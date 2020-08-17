package com.clive.service.impl;

import java.util.List;

import com.clive.bean.Category;
import com.clive.common.StoreResult;
import com.clive.dao.CategoryDao;
import com.clive.dao.impl.CategoryDaoImpl;
import com.clive.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	//dao 里面写sql
	private CategoryDao categoryDao = new CategoryDaoImpl();
	public StoreResult addCategory(Category category) {
		/**
		 * service 要处理逻辑  逻辑等下来写
		 */
		boolean isCategoryName = checkCategoryName(category.getCategoryName());
		if(isCategoryName){
			//如果分类已经存在 我们在添加一个相同的分类 应该是吧
			return StoreResult.build(500, "添加失败,分类名称重复", null);
		}
		int count = categoryDao.addCategory(category);
		if(count == 0){
			return StoreResult.build(500, "添加失败", null);
		}

		return StoreResult.build(200, "添加成功", null);
	}

	public StoreResult deleteCategoryById(int categoryId) {
		int count = categoryDao.deleteCategoryById(categoryId);
		if(count == 0){
			return StoreResult.build(500, "删除失败", null);
		}
		return StoreResult.ok(200,"删除成功");
	}

	public StoreResult updateCategory(Category category) {
		Category isCategory = null;
		//页面传递了个id 过来 我根据id 可以查询到指定的分类信息
		isCategory = categoryDao.findCategoryById(category.getId());
		if(isCategory==null){
			return StoreResult.build(500, "修改失败,该分类不存在", null);
		}
		isCategory = categoryDao.checkCategoryName(category.getCategoryName());
		if(isCategory!=null){
			return StoreResult.build(500, "修改失败,您要修改的分类名称已经存在了", null);
		}
		//因为逻辑比较复杂 所以我们现在做基础的逻辑判断
		int count = categoryDao.updateCategory(category);
		if(count == 0){
			return StoreResult.build(500, "修改失败", null);
		}
		return StoreResult.ok(200,"修改成功");
	}

	public Category findCategoryById(int categoryId) {
		/**
		 *  这里要加入缓存逻辑
		 *   还要涉及到缓存同步问题 缓存穿透问题 缓存雪崩问题
		 *  redis MongoDB 
		 */
		
		Category category = categoryDao.findCategoryById(categoryId);
		
		return category;
	}

	public List<Category> findCategorys() {
		List<Category> categorys = categoryDao.findCategorys();
		return categorys;
	}

	@Override
	public boolean checkCategoryName(String categoryName) {
		Category category = categoryDao.checkCategoryName(categoryName);
		if(category !=null){
			return true;
		}
		return false;
	}

}
