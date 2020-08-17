package com.clive.dao;

import java.util.List;

import com.clive.bean.Category;

public interface CategoryDao {
	/**
	 * 添加一个分类信息到数据库中categorys表里面
	 * @param category 需要添加的书籍分类信息
	 * @return 记录条数 如果为0则表示添加失败 如果大于0则表示添加成功 
	 */
	int addCategory(Category category);
	/**
	 * 从数据库中 删除书籍分类信息
	 * @param categoryId 需要删除的分类id
	 * @return 记录条数 如果为0则表示添加失败 如果大于0则表示添加成功 
	 */
	int deleteCategoryById(int categoryId);
	/**
	 * 修改数据库中分类表中的分类信息
	 * @param category 根据分类id 修改分类信息
	 * @return 记录条数 如果为0则表示添加失败 如果大于0则表示添加成功 
	 */
	int updateCategory(Category category);
	/**
	 * 从数据库中 查询分类中的分类信息 根据分类id查询
	 * @param categoryId 分类id
	 * @return 指定分类id的分类信息 如果返回null则表示 该分类id没有分类信息
	 */
	Category findCategoryById(int categoryId);
	/**
	 * 从数据库中 查询所有分类信息
	 * @return 所有分类信息的List集合对象
	 */
	List<Category> findCategorys();
	/**
	 * 根据分类名称查询分类信息
	 * @param categoryName 分类名称
	 * @return
	 */
	Category checkCategoryName(String categoryName);

}
