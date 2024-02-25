package com.ravi.quizapp.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.quizapp.entity.exam.Category;
import com.ravi.quizapp.service.ICategoryService;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryRestController {

	@Autowired
	private ICategoryService categoryService;

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Category> addcategory(@RequestBody Category category) {
		Category categoryResponse = categoryService.addCategory(category);
		return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
		Category categoryResponse = categoryService.updateCategory(category);
		return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId) {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>("Category Deleted with Id "+categoryId,HttpStatus.OK);
	}

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Category>> getCategories() {
		List<Category> categories = categoryService.getCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@GetMapping(value = "/{categoryId}")
	public ResponseEntity<Category> getCategory(@PathVariable Integer categoryId) {
		Category category = categoryService.getCategory(categoryId);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}


}
