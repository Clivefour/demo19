package com.clive.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.clive.bean.Book;
import com.clive.bean.Category;
import com.clive.dao.CategoryDao;
import com.clive.utils.DbcpUtils;

public class CategoryDaoImpl implements CategoryDao {

	public int addCategory(Category category) {
		Connection conn = DbcpUtils.getConnection();
		String sql = "INSERT INTO categorys(categoryName,des) VALUE(?,?)";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, category.getCategoryName());
			pst.setString(2, category.getDes());
			int count = pst.executeUpdate();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbcpUtils.closeAll(conn, pst, null);
		}
		
		
		return 0;
	}

	public int deleteCategoryById(int categoryId) {
		Connection conn = DbcpUtils.getConnection();
		Connection conn2 = DbcpUtils.getConnection();
		String sql = "DELETE FROM categorys WHERE id = ?";
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, categoryId);
			int count = pst.executeUpdate();
			String sql2 = "DELETE FROM books WHERE categoryId = ?";
			pst2 = conn2.prepareStatement(sql2);
			pst2.setInt(1, categoryId);
			count = pst2.executeUpdate();
			
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbcpUtils.closeAll(conn, pst, null);
			DbcpUtils.closeAll(conn2, pst2, null);
		}
		
		
		return 0;
	}

	public int updateCategory(Category category) {
		Connection conn = DbcpUtils.getConnection();
		String sql = "UPDATE categorys SET categoryName = ? , des = ? WHERE id = ?";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, category.getCategoryName());
			pst.setString(2, category.getDes());
			pst.setInt(3, category.getId());
			int count = pst.executeUpdate();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbcpUtils.closeAll(conn, pst, null);
		}
		return 0;
	}

	public Category findCategoryById(int categoryId) {
		Connection conn = DbcpUtils.getConnection();
		Connection conn2 = DbcpUtils.getConnection();
		String sql = "SELECT * FROM categorys WHERE id = ?";
		String sql2 = "SELECT * FROM books WHERE categoryId = ?";
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, categoryId);
			resultSet = pst.executeQuery();
			if(resultSet.next()){
				Category category = new Category();
				category.setId(resultSet.getInt("id"));
				category.setCategoryName(resultSet.getString("categoryName"));
				category.setDes(resultSet.getString("des"));
				pst2 = conn2.prepareStatement(sql2);
				pst2.setInt(1, categoryId);
				resultSet2 = pst2.executeQuery();
				List<Book> books = new ArrayList<Book>();
				while (resultSet2.next()) {
					Book book = new Book();
					book.setId(resultSet2.getInt("id"));
					book.setBookName(resultSet2.getString("bookName"));
					book.setSellPoint(resultSet2.getString("sellPoint"));
					book.setPrice(resultSet2.getInt("price"));
					book.setPic(resultSet2.getString("pic"));
					book.setDes(resultSet2.getString("des"));
					//因为所有书籍用的同一个分类
					book.setCategory(category);
					books.add(book);
				}
				
				//建立 book与category之间的关系
				category.setBooks(books);
				return category;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbcpUtils.closeAll(conn, pst, resultSet);
		}
		return null;
	}

	public List<Category> findCategorys() {
		Connection conn = DbcpUtils.getConnection();
		Connection conn2 = DbcpUtils.getConnection();
		String sql = "SELECT * FROM categorys";
		String sql2 = "SELECT * FROM books WHERE categoryId = ?";
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		try {
			pst = conn.prepareStatement(sql);
			
			resultSet = pst.executeQuery();
			List<Category> categorys = new ArrayList<Category>();
			while(resultSet.next()){
				Category category = new Category();
				category.setId(resultSet.getInt("id"));
				category.setCategoryName(resultSet.getString("categoryName"));
				category.setDes(resultSet.getString("des"));
			
				pst2 = conn2.prepareStatement(sql2);
				pst2.setInt(1, category.getId());
				resultSet2 = pst2.executeQuery();
				List<Book> books = new ArrayList<Book>();
				while (resultSet2.next()) {
					Book book = new Book();
					book.setId(resultSet2.getInt("id"));
					book.setBookName(resultSet2.getString("bookName"));
					book.setSellPoint(resultSet2.getString("sellPoint"));
					book.setPrice(resultSet2.getInt("price"));
					book.setPic(resultSet2.getString("pic"));
					book.setDes(resultSet2.getString("des"));
					//因为所有书籍用的同一个分类
					book.setCategory(category);
					books.add(book);
				}
				
				//建立 book与category之间的关系
				category.setBooks(books);
				categorys.add(category);
			}
			return categorys;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbcpUtils.closeAll(conn, pst, resultSet);
			DbcpUtils.closeAll(conn2, pst2, resultSet2);
		}
		return null;
	}

	@Override
	public Category checkCategoryName(String categoryName) {
		Connection conn = DbcpUtils.getConnection();
		String sql = "SELECT * FROM categorys WHERE categoryName = ?";
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, categoryName);
			resultSet = pst.executeQuery();
			if(resultSet.next()){
				Category category = new Category();
				category.setId(resultSet.getInt("id"));
				category.setCategoryName(resultSet.getString("categoryName"));
				category.setDes(resultSet.getString("des"));
				return category;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbcpUtils.closeAll(conn, pst, resultSet);
		}
		return null;
	}

}
