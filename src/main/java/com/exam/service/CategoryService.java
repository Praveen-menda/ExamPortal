package com.exam.service;

import com.exam.model.Exam.Category;
import com.exam.model.Exam.Quiz;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    public Category addCategory(Category category) throws Exception;
    public void removeCategory(String categoryName) throws Exception;
    public List<Category> findAllCategory() throws Exception;

    Category updateCategory(Category category) throws Exception;

    List<Quiz> findQuizById(Long id);
}
