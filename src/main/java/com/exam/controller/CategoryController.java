package com.exam.controller;

import com.exam.model.Exam.Category;
import com.exam.service.CategoryService;
import com.exam.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping({"/user/category","/admin/category/"})
    public ResponseEntity<?> showAllCategory()
    {
        try {
            List<Category> categoryList = categoryService.findAllCategory();
            return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }
    @PostMapping("/admin/category/")
    public ResponseEntity<?> addCategory(@RequestBody Category category)
    {
        try {

            if(category.getCname() == null)
            {
                return new ResponseEntity<String>("Enter Category Name",HttpStatus.FAILED_DEPENDENCY);
            }
            Category category1 = categoryService.addCategory(category);
            return new ResponseEntity<Category>(category1, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }
    @PutMapping("/admin/category/")
    public ResponseEntity<?> editCategory(@RequestBody Category category)
    {
        try {
            Category category1 = categoryService.updateCategory(category);
            return new ResponseEntity<Category>(category1, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }
    @DeleteMapping("/admin/category/{cname}")
    public ResponseEntity<?> deleteCategory(@PathVariable("cname") String category)
    {
        try {
             categoryService.removeCategory(category);
            return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }
}
