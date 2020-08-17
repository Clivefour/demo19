package com.clive.test;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.clive.bean.Book;
import com.clive.bean.Category;
import com.clive.common.StoreResult;
import com.clive.dao.CategoryDao;
import com.clive.dao.impl.CategoryDaoImpl;
import com.clive.service.CategoryService;
import com.clive.service.impl.CategoryServiceImpl;
import com.clive.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CategoryTest {
	private CategoryDao dao;
	private CategoryService service;
	@Before
	public void init(){
		dao = new CategoryDaoImpl();
		service = new CategoryServiceImpl();
	}
	
	@Test
	public void addDao(){
		Category category = new Category();
		category.setCategoryName("玄幻类");
		category.setDes("修仙，打boss");
		dao.addCategory(category);
	}
	@Test
	public void deleteDao(){
		int deleteCategoryById = dao.deleteCategoryById(1);
	}
	@Test
	public void updateDao(){
		Category category = new Category();
		category.setId(1);
		category.setCategoryName("言情类");
		category.setDes("爱的死去活来");
		dao.updateCategory(category);
	}
	@Test
	public void findOneDao(){
		Category category = dao.findCategoryById(1);
		System.out.println(category);
	}
	@Test
	public void findDao(){
		List<Category> findCategorys = dao.findCategorys();
		for (Category category : findCategorys) {
			System.out.println(category);
			List<Book> books = category.getBooks();
			if(books.size()>0&&books!=null){
				for (Book book : books) {
					System.out.println("每一个分类对应的书籍对象"+book);
				}
			}
			
			System.out.println("-------------------");
		}
	}
	@Test
	public void addService(){
		Category category = new Category();
		category.setCategoryName("玄幻类");
		category.setDes("修仙，打boss");
		StoreResult addCategory = service.addCategory(category);
		String objectToJson = JsonUtils.objectToJson(addCategory);
		System.out.println(objectToJson);
	}
	@Test
	public void deleteService(){
		StoreResult storeResult = service.deleteCategoryById(6);
		String objectToJson = JsonUtils.objectToJson(storeResult);
		System.out.println(objectToJson);
	}
	@Test
	public void updateService(){
		Category category = new Category();
		category.setId(1);
		category.setCategoryName("言情类2");
		category.setDes("哈哈哈哈1111");
		StoreResult updateCategory = service.updateCategory(category);
		String objectToJson = JsonUtils.objectToJson(updateCategory);
		System.out.println(objectToJson);
	}
	@Test
	public void findOneService() throws JsonProcessingException{
		Category category = service.findCategoryById(8);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(category);
		System.out.println(json);
	}
	@Test
	public void findAllService(){
		List<Category> findCategorys = service.findCategorys();
		for (Category category : findCategorys) {
			System.out.println(category);
		}
	}
}
