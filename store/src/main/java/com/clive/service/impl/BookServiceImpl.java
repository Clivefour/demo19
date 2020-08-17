package com.clive.service.impl;

import java.util.List;

import com.clive.bean.Book;
import com.clive.common.Constant;
import com.clive.common.StoreResult;
import com.clive.dao.BookDao;
import com.clive.dao.impl.BookDaoImpl;
import com.clive.service.BookService;

public class BookServiceImpl implements BookService {
	private BookDao bookDao = new BookDaoImpl();
	@Override
	public StoreResult addBook(Book book) {
		int count = bookDao.addBook(book);
		if(count==0){
			return StoreResult.build(500, "添加书籍失败", null);
		}

		return StoreResult.ok(200, "添加书籍成功");
	}

	@Override
	public StoreResult deleteBook(int bookId) {
		int count = bookDao.deleteBook(bookId);
		return null;
	}

	@Override
	public StoreResult updateBook(Book book) {
		int count = bookDao.updateBook(book);
		return null;
	}

	@Override
	public Book findBookById(int bookId) {
		Book book = bookDao.findBookById(bookId);
		return null;
	}

	@Override
	public List<Book> findBooks() {
		List<Book> books = bookDao.findBooks();
		return books;
	}

	@Override
	public List<Book> findBooksByPage(Integer current) {
		List<Book> books = bookDao.findBooksByPage(current);
		return books;
	}

	@Override
	public Integer findBookTotalPage() {
		int count = bookDao.totalCount();
		int totalPage = 
				count%Constant.PAGESIZE==0?
						count/Constant.PAGESIZE:
							(count/Constant.PAGESIZE)+1;
		return totalPage;
	}

}
