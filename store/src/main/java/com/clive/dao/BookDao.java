package com.clive.dao;

import java.util.List;

import com.clive.bean.Book;

public interface BookDao {
	/**
	 * 添加书籍信息到数据库中注意要把categoryId也添加进去
	 * @param book  需要添加的书籍对象
	 * @return 执行的sql的条数
	 */
	int addBook(Book book);
	/**
	 * 从数据库中根据书籍id删除数据信息
	 * @param bookId 书籍id
	 * @return 执行的sql的条数
	 */
	int deleteBook(int bookId);
	/**
	 * 从数据库中修改书籍信息 注意他可以修改数据所属分类信息的
	 * @param book 书籍对象
	 * @return 执行的sql的条数
	 */
	int updateBook(Book book);
	/**
	 * 根据书籍id查询数据库中指定书籍信息
	 * @param bookId 书籍id
	 * @return 指定书籍id所对应的书籍对象
	 */
	Book findBookById(int bookId);
		
	/**
	 * 查询所有书籍信息 
	 * @return 所有书籍信息 以及每一本书对应的分类信息
	 */
	List<Book> findBooks();
	/**
	 * 分页查询数据库中书籍信息 
	 * @param current
	 * @return 分页显示书籍信息
	 */
	List<Book> findBooksByPage(Integer current);
	/**
	 * 得到总记录条数
	 * @return
	 */
	int totalCount();

}
