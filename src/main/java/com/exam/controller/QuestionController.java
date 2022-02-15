package com.exam.controller;

import com.exam.model.Exam.Question;
import com.exam.model.Exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.impl.QuestionServiceImpl;
import com.exam.service.impl.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private QuizServiceImpl quizService;

    @GetMapping({"/user/question/{quizId}","/admin/question/{quizId}"})
    public ResponseEntity<?> getQuestionByQuiz(@PathVariable("quizId")Long quizId)
    {

        try {
            List<Question> quesList = quizService.findQuestionByQuizId(quizId);
            if(quesList.size() >0)
                return new ResponseEntity<List<Question>>(quesList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return null;
    }
    @PostMapping("/admin/quiz/question/")
    public ResponseEntity<?> addCategoryQuiz(@RequestBody Question ques)
    {
        try {
            Question quiz1 = questionService.addQuestion(ques,ques.getQid());
            return new ResponseEntity<Question>(quiz1, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }
    @PutMapping("/admin/quiz/question/")
    public ResponseEntity<?> editCategory(@RequestBody Question ques)
    {
        try {
            Question question = questionService.updateQueston(ques);
            return new ResponseEntity<Question>(question, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }
    @DeleteMapping("/admin/quiz/question/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id)
    {
        try {
            questionService.removeQuestion(id);
            return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }

}
