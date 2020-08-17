package com.clive.service;

import java.util.List;

import com.clive.bean.Book;
import com.clive.common.StoreResult;

public interface BookService {
	/**
	 * 添加书籍信息，注意事项
	 * 1.pic是图片路径
	 * 2.要添加分类信息进去
	 * @param book 需要添加的书籍对象 里面包含了分类对象
	 * @return 定义一个标准的返回结果集 code代表状态 msg代表返回给页面的提示信息 data代表数据
	 */
	StoreResult addBook(Book book);
	/**
	 * 根据书籍id 删除指定书籍信息
	 * @param bookId 
	 * @return 定义一个标准的返回结果集 code代表状态 msg代表返回给页面的提示信息 data代表数据
	 */
	StoreResult deleteBook(int bookId);
	/**
	 * 根据书籍id 修改指定书籍信息 注意 有可能要修改书籍所属分类
	 * @param book 需要修改的书籍对象信息
	 * @return 定义一个标准的返回结果集 code代表状态 msg代表返回给页面的提示信息 data代表数据
	 */
	StoreResult updateBook(Book book);
	/**
	 * 根据书籍id 查询指定书籍信息 
	 * @param bookId 书籍id
	 * @return 定义一个标准的返回结果集 code代表状态 msg代表返回给页面的提示信息 data代表数据
	 */
	Book findBookById(int bookId);
	/**
	 * 查询所有书籍信息 以及每一本数据所对应的书籍分类信息
	 * @return 定义一个标准的返回结果集 code代表状态 msg代表返回给页面的提示信息 data代表数据
	 */
	List<Book> findBooks();
	/**
	 * 分页显示商品信息
	 * @param current 当前页码
	 * @return 当前页码的所有书籍信息
	 */
	List<Book> findBooksByPage(Integer current);
	/**
	 * 得到总页数
	 * @return 书籍总页数
	 */
	Integer findBookTotalPage();
	
}	
