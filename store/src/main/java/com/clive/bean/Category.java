package com.clive.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
public class Category implements Serializable{
	private int id;
	//如果值为null 则忽略不显示  如果不为null则显示
	private String categoryName;
	private String des;
	//解决表与表之前嵌套 导致无线循环解析问题
	private List<Book> books;
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", des=" + des + "]";
	}
	
	
}
