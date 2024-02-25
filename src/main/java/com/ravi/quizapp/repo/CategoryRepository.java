package com.ravi.quizapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravi.quizapp.entity.exam.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
