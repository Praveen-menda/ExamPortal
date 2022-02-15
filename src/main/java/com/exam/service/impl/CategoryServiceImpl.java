package com.exam.service.impl;

import com.exam.model.Exam.Category;
import com.exam.model.Exam.Quiz;
import com.exam.repo.CategoryRepo;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo repo;
    @Override
    public Category addCategory(Category category) throws Exception {

        try {
           Optional <Category> cat = Optional.ofNullable(repo.findByCname(category.getCname()));
            if(!cat.isEmpty()) {

                throw new Exception("Category Name Always there");
            }
             return repo.save(category);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            if(e.getMessage().equalsIgnoreCase("Category Name Always there"))
                throw new Exception("Category Name Always there");
            throw new Exception("Unable to add");
        }

    }

    @Override
    public void removeCategory(String categoryName) throws Exception {
       Optional <Category> category = Optional.ofNullable(repo.findByCname(categoryName));
        try {
            repo.delete(category.get());
        }
        catch (Exception e)
        {
            e.printStackTrace();
           throw new Exception("Unable to delete");
        }

    }

    @Override
    public List<Category> findAllCategory() throws Exception {
        try {
            return repo.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception("Unable to add");
        }
    }

    @Override
    public Category updateCategory(Category category) throws Exception {
        try {
            return repo.save(category);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception("Unable to update");
        }
    }

    @Override
    public List<Quiz> findQuizById(Long id) {
        Optional<Category> category = repo.findById(id);
        List<Quiz> quizList = new ArrayList<>();
        if(category.isPresent())
        {
         quizList = category.get().getQuizzes();
        }
        return quizList;
    }
}
