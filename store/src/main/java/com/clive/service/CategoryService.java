package com.clive.service;

import java.util.List;

import com.clive.bean.Category;
import com.clive.common.StoreResult;

public interface CategoryService {
	/**
	 * 添加书籍分类信息 ，注意要验证分类名称是否重复
	 * @param category 需要添加的分类对象
	 * @return 定义一个标准的返回结果集 code代表状态 msg代表返回给页面的提示信息 data代表数据
	 */
	StoreResult addCategory(Category category);
	/**
	 * 根据分类id删除分类信息
	 * @param categoryId 需要删除的分类id
	 * @return 定义一个标准的返回结果集 code代表状态 msg代表返回给页面的提示信息 data代表数据
	 */
	StoreResult deleteCategoryById(int categoryId);
	/**
	 * 根据分类id修改分类信息
	 * @param category 需要修改的分类信息 以及 修改那个分类的 分类id
	 * @return 定义一个标准的返回结果集 code代表状态 msg代表返回给页面的提示信息 data代表数据
	 */
	StoreResult updateCategory(Category category);
	/**
	 * 根据分类id查询指定分类信息
	 * @param categoryId 分类id
	 * @return 指定分类id的分类信息
	 */
	Category findCategoryById(int categoryId);
	
	/**
	 * 查询所有分类信息 
	 * @return 一个list集合里面包含了所有分类信息
	 */
	List<Category> findCategorys();
	/**
	 * 校验分类名称是否存在 
	 * @param categoryName 分类名称
	 * @return true 表示存在 false 表示不存在
	 */
	boolean checkCategoryName(String categoryName);
	
}
