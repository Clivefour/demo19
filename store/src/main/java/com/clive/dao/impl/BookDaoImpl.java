package com.clive.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.clive.bean.Book;
import com.clive.bean.Category;
import com.clive.common.Constant;
import com.clive.dao.BookDao;
import com.clive.service.CategoryService;
import com.clive.service.impl.CategoryServiceImpl;
import com.clive.utils.DbcpUtils;

public class BookDaoImpl implements BookDao {

	@Override
	public int addBook(Book book) {
		Connection conn = DbcpUtils.getConnection();
		String sql = "INSERT INTO books(bookName,sellPoint,price,pic,des,categoryId) VALUE(?,?,?,?,?,?)";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, book.getBookName());
			pst.setString(2, book.getSellPoint());
			pst.setInt(3, book.getPrice());
			pst.setString(4, book.getPic());
			pst.setString(5, book.getDes());
			//获取当前书籍对象 所属分类对象
			Category category = book.getCategory();
			//设置分类id
			pst.setInt(6, category.getId());

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

	@Override
	public int deleteBook(int bookId) {
		Connection conn = DbcpUtils.getConnection();
		String sql = "DELETE FROM books WHERE id = ?";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, bookId);

			int count = pst.executeUpdate();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbcpUtils.closeAll(conn, pst, null);
		}
		return 0;
	}

	@Override
	public int updateBook(Book book) {
		Connection conn = DbcpUtils.getConnection();
		String sql = "UPDATE books SET bookName = ? , sellPoint = ? , price = ? , pic = ? , des = ? ,categoryId = ? WHERE id = ?";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, book.getBookName());
			pst.setString(2, book.getSellPoint());
			pst.setInt(3, book.getPrice());
			pst.setString(4, book.getPic());
			pst.setString(5, book.getDes());
			//从书籍对象里面获取 分类对象
			Category category = book.getCategory();
			//吧分类对象的id 设置到 需要修改的 sql语句里面去
			pst.setInt(6, category.getId());
			pst.setInt(7, book.getId());

			int count = pst.executeUpdate();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbcpUtils.closeAll(conn, pst, null);
		}
		return 0;
	}

	@Override
	public Book findBookById(int bookId) {
		Connection conn = DbcpUtils.getConnection();
		String sql = "SELECT * FROM books WHERE id = ?";
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, bookId);
			resultSet = pst.executeQuery();
			if(resultSet.next()){
				Book book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setBookName(resultSet.getString("bookName"));
				book.setSellPoint(resultSet.getString("sellPoint"));
				book.setPrice(resultSet.getInt("price"));
				book.setPic(resultSet.getString("pic"));
				book.setDes(resultSet.getString("des"));
				int categoryId = resultSet.getInt("categoryId");
				CategoryService categoryService = new CategoryServiceImpl();
				Category category = categoryService.findCategoryById(categoryId);
				book.setCategory(category);
				return book;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbcpUtils.closeAll(conn, pst, resultSet);
		}
		return null;
	}

	@Override
	public List<Book> findBooks() {
		Connection conn = DbcpUtils.getConnection();
		String sql = "SELECT * FROM books";
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		CategoryService categoryService = new CategoryServiceImpl();
		try {
			pst = conn.prepareStatement(sql);
			resultSet = pst.executeQuery();
			List<Book> books = new ArrayList<Book>();
			while(resultSet.next()){
				Book book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setBookName(resultSet.getString("bookName"));
				book.setSellPoint(resultSet.getString("sellPoint"));
				book.setPrice(resultSet.getInt("price"));
				book.setPic(resultSet.getString("pic"));
				book.setDes(resultSet.getString("des"));
				int categoryId = resultSet.getInt("categoryId");
				Category category = categoryService.findCategoryById(categoryId);
				book.setCategory(category);
				books.add(book);
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbcpUtils.closeAll(conn, pst, resultSet);
		}
		return null;
	}

	@Override
	public List<Book> findBooksByPage(Integer current) {
		Connection conn = DbcpUtils.getConnection();
		String sql = "SELECT * FROM books LIMIT ?,?";
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		CategoryService categoryService = new CategoryServiceImpl();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, (current-1)*Constant.PAGESIZE);
			pst.setInt(2, Constant.PAGESIZE);
			resultSet = pst.executeQuery();
			List<Book> books = new ArrayList<Book>();
			while(resultSet.next()){
				Book book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setBookName(resultSet.getString("bookName"));
				book.setSellPoint(resultSet.getString("sellPoint"));
				book.setPrice(resultSet.getInt("price"));
				book.setPic(resultSet.getString("pic"));
				book.setDes(resultSet.getString("des"));
				int categoryId = resultSet.getInt("categoryId");
				Category category = categoryService.findCategoryById(categoryId);
				book.setCategory(category);
				books.add(book);
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbcpUtils.closeAll(conn, pst, resultSet);
		}
		return null;
	}

	@Override
	public int totalCount() {
		Connection conn = DbcpUtils.getConnection();
		String sql = "SELECT COUNT(*) FROM  books";
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		try {
			pst = conn.prepareStatement(sql);
			resultSet = pst.executeQuery();
			if(resultSet.next()){
				return resultSet.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbcpUtils.closeAll(conn, pst, resultSet);
		}
		return 0;
	}

}
