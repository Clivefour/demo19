package com.clive.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.clive.bean.Book;
import com.clive.bean.Category;
import com.clive.dao.BookDao;
import com.clive.dao.impl.BookDaoImpl;
import com.clive.service.BookService;
import com.clive.service.impl.BookServiceImpl;
import com.clive.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class BookTest {
	private BookService bookService;
	private BookDao bookDao;
	@Before
	public void init(){
		bookService = new BookServiceImpl();
		bookDao = new BookDaoImpl();
	}
	@Test
	public void daoAdd(){
		Book book = new Book();
		book.setBookName("斗破苍穹");
		book.setSellPoint("吴磊翻拍");
		book.setPrice(45);
		book.setPic("d://1.jpg");
		book.setDes("斗宗恐怖如斯");
		Category category = new Category();
		category.setId(8);
		category.setCategoryName("玄幻类");
		category.setDes("修仙");
		book.setCategory(category);
		int addBook = bookDao.addBook(book);
		System.out.println(addBook);
	}
	@Test
	public void findOneBook(){
		Book book = bookDao.findBookById(2);
		String json = JsonUtils.objectToJson(book);
		System.out.println(book);
		System.out.println(book.getCategory());
		System.out.println(json);
		
	}
	@Test
	public void findOneBooks(){
		List<Book> findBooks = bookDao.findBooks();
		
		for (Book book : findBooks) {
			System.out.println(book);
			Category category = book.getCategory();
			System.out.println(category);
		}
		
	}
	@Test
	public void updateBook(){
		Book book = new Book();
		book.setId(1);
		book.setBookName("完美世界");
		book.setSellPoint("完美嘛");
		book.setPrice(55);
		book.setPic("d://1.jpg");
		book.setDes("很好的世界");
		Category category = new Category();
		category.setId(1);
		category.setCategoryName("言情类");
		category.setDes("爱的死去活来");
		book.setCategory(category);
		int updateBook = bookDao.updateBook(book);
		System.out.println(updateBook);
		
		
	}
	@Test
	public void deleteBook(){
		int deleteBook = bookDao.deleteBook(1);
		
		
	}
	
}
