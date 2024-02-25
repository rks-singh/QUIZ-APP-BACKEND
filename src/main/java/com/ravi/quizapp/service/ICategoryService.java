package com.ravi.quizapp.service;

import java.util.List;

import com.ravi.quizapp.entity.exam.Category;

public interface ICategoryService {

	public Category addCategory(Category category);
	public Category updateCategory(Category category);
	public void deleteCategory(Integer categoryId);
	public List<Category> getCategories();
	public Category getCategory(Integer categoryId);
}
