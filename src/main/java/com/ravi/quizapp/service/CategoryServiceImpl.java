package com.ravi.quizapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.quizapp.entity.exam.Category;
import com.ravi.quizapp.exception.CategoryNotFoundException;
import com.ravi.quizapp.repo.CategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public Category addCategory(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Optional<Category> category = categoryRepo.findById(categoryId);
		if (category.isEmpty()) {
			throw new CategoryNotFoundException("Category is not available with Id " + categoryId);
		} else {
			categoryRepo.deleteById(categoryId);
		}
	}

	@Override
	public List<Category> getCategories() {
		List<Category> categories = categoryRepo.findAll();
		if (categories.isEmpty()) {
			throw new CategoryNotFoundException("Categories are not Available.");
		} else {
			return categories;
		}
	}

	@Override
	public Category getCategory(Integer categoryId) {
		Optional<Category> category = categoryRepo.findById(categoryId);
		if (category.isEmpty()) {
			throw new CategoryNotFoundException("Category Not Found with given Id " + categoryId);
		} else {
			return category.get();
		}
	}

}
