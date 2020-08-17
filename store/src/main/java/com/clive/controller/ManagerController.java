package com.clive.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clive.bean.Book;
import com.clive.bean.Category;
import com.clive.common.BookVo;
import com.clive.common.StoreResult;
import com.clive.service.BookService;
import com.clive.service.CategoryService;
import com.clive.service.impl.BookServiceImpl;
import com.clive.service.impl.CategoryServiceImpl;
import com.clive.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * Servlet implementation class ManagerController
 */
public class ManagerController extends HttpServlet {
	private CategoryService categoryService;
	private BookService bookService;
	@Override
	public void init() throws ServletException {
		super.init();
		categoryService = new CategoryServiceImpl();
		bookService = new BookServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if("checkCategoryName".equals(op)){
			checkCategoryName(request,response);
		}else if("addCategory".equals(op)){
			addCategory(request,response);
		}else if("showCategoryAll".equals(op)){
			showCategoryAll(request,response);
		}else if("deleteCategory".equals(op)){
			deleteCategory(request,response);
		}else if("showUpdateCategory".equals(op)){
			showUpdateCategory(request,response);
		}else if("updateCategory".equals(op)){
			updateCategory(request,response);
		}else if("findCategorys".equals(op)){
			findCategorys(request,response);
		}else if("findBooks".equals(op)){
			findBooks(request,response);
		}
	}
	/**
	 * 查询所有书籍信息 以及书籍信息所对应的 分类信息
	 */
	private void findBooks(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String current = request.getParameter("current");
		//每一页显示的书籍信息
		List<Book> findBooks = bookService.findBooksByPage(Integer.valueOf(current));
		
		//多少页
		Integer totalPage = bookService.findBookTotalPage();
		
		List<BookVo> bookVos = new ArrayList<>();
		for (Book book : findBooks) {
			BookVo bookVo = new BookVo();
			Category category = book.getCategory();
			bookVo.setCategoryId(category.getId());
			bookVo.setCategoryName(category.getCategoryName());
			bookVo.setCategoryDes(category.getDes());
			book.setCategory(null);
			bookVo.setBook(book);
			bookVo.setTotalPage(totalPage);
			bookVos.add(bookVo);
		}
		String json = JsonUtils.objectToJson(bookVos);
		response.getWriter().print(json);
		
	}

	/**
	 * 查询所有分类信息 ，吧分类信息集合对象变成json发送到页面
	 * 绑定到select之上
	 */
	private void findCategorys(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		List<Category> categorys = categoryService.findCategorys();
		for (Category category : categorys) {
			category.setBooks(null);
		}
		String json = JsonUtils.objectToJson(categorys);
		response.getWriter().print(json);
	}

	/**
	 * 修改书籍分类信息
	 */
	private void updateCategory(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String id = request.getParameter("id");

		String categoryName = request.getParameter("categoryName");
		String des = request.getParameter("des");
		Category category = new Category();
		category.setId(Integer.valueOf(id));
		category.setCategoryName(categoryName);
		category.setDes(des);

		StoreResult result = categoryService.updateCategory(category);

		//本来说应该吧提示信息发送到页面 但是呢由于bootstrap 有点难做 不做了
		response.sendRedirect("/store/manager/ManagerController?op=showCategoryAll");


	}

	/**
	 * 根据分类id查询得到指定的分类信息并且展示在模态框里面用作修改操作
	 */
	private void showUpdateCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//获取分类id
		String categoryId = request.getParameter("id");
		//根据分类id 得到指定的分类对象
		Category category = categoryService.findCategoryById(Integer.valueOf(categoryId));
		String json = JsonUtils.objectToJson(category);
		response.getWriter().print(json);
	}

	/**
	 * 根据分类id删除分类信息 并且展示分类列表
	 */
	private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String categoryId = request.getParameter("categoryId");
		StoreResult result = categoryService.deleteCategoryById(Integer.valueOf(categoryId));
		String json = JsonUtils.objectToJson(result);
		response.getWriter().print(json);

	}

	/**
	 * 展示所有分类信息
	 */
	private void showCategoryAll(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<Category> categorys = categoryService.findCategorys();
		request.setAttribute("categorys", categorys);
		request.getRequestDispatcher("/manager/showCategory.jsp").forward(request, response);
	}

	/**
	 * 	添加书籍分类信息
	 */
	private void addCategory(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//根据页面传递的key 取value
		String categoryName = request.getParameter("categoryName");
		String des = request.getParameter("des");
		Category category = new Category();
		category.setCategoryName(categoryName);
		category.setDes(des);
		//之前service写好的代码
		StoreResult result = categoryService.addCategory(category);
		String json = JsonUtils.objectToJson(result);
		response.getWriter().print(json);
	}

	/**
	 * 校验书籍分类名称是否可用方法
	 */
	private void checkCategoryName(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String categoryName = request.getParameter("categoryName");
		categoryName = new String(categoryName.getBytes("ISO-8859-1"),"UTF-8");
		boolean isCategoryName = categoryService.checkCategoryName(categoryName);
		if(isCategoryName){
			//不可用
			String json = JsonUtils.objectToJson(StoreResult.ok(500, "分类名称重复"));
			response.getWriter().print(json);
		}else{
			//可用
			String json = JsonUtils.objectToJson(StoreResult.ok(200, "分类名称可用"));
			response.getWriter().print(json);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
