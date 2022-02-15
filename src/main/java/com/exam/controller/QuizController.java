package com.exam.controller;

import com.exam.model.Exam.Category;
import com.exam.model.Exam.Quiz;
import com.exam.service.impl.CategoryServiceImpl;
import com.exam.service.impl.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class QuizController {

    @Autowired
    private QuizServiceImpl quizService;
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping({"/user/quiz/{catId}","/admin/quiz/{catId}"})
    public ResponseEntity<?> showQuizByCategory(@PathVariable ("catId") Long id)
    {
        try {
            List<Quiz> quizList = categoryService.findQuizById(id);
            if(quizList.size() >0)
            return new ResponseEntity<List<Quiz>>(quizList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

       return null;
    }
    @PostMapping("/admin/quiz/")
    public ResponseEntity<?> addCategoryQuiz(@RequestBody Quiz quiz)
    {
        try {
            Quiz quiz1 = quizService.addQuiz(quiz,quiz.getCatId());
            return new ResponseEntity<Quiz>(quiz1, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }
    @PutMapping("/admin/quiz/")
    public ResponseEntity<?> editCategory(@RequestBody Quiz quiz)
    {
        try {
            Quiz quiz1 = quizService.updateQuiz(quiz);
            return new ResponseEntity<Quiz>(quiz1, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }
    @DeleteMapping("/admin/quiz/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id)
    {
        try {
            quizService.removeQuiz(id);
            return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }
}
