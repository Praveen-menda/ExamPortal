package com.exam.service.impl;

import com.exam.model.Exam.Category;
import com.exam.model.Exam.Question;
import com.exam.model.Exam.Quiz;
import com.exam.repo.CategoryRepo;
import com.exam.repo.QuizRepo;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepo repo ;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public List<Quiz> findQuizById(Long id) {
        try {
         List<Quiz> quiz = repo.findQuizByCategoryId(id);
            return quiz;
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public Quiz addQuiz(Quiz quiz, Long id) throws Exception {

            Category category = categoryRepo.getById(id);
            if(category.getCname() == null)
            {
                throw new Exception("Category Id is incorrect");
            }
            quiz.setCategory(category);
          Optional <Quiz> quiz1 = Optional.ofNullable(repo.findByQtitle(quiz.getQtitle()));
          if(!quiz1.isEmpty()) {

            throw new Exception("Quiz Name Already there");
        }
          Quiz quiz2 = repo.save(quiz);
        return quiz2;
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) throws Exception {
        Optional<Category> category = Optional.of(categoryRepo.getById(quiz.getCatId()));
        if(category.isEmpty())
        {
            throw new Exception("Category Id is incorrect");
        }
        quiz.setCategory(category.get());
        return repo.save(quiz);
    }

    @Override
    public void removeQuiz(Long id) {
       Optional<Quiz> quiz = Optional.of(repo.getById(id));
       if(quiz.isPresent())
       repo.delete(quiz.get());

    }

    public List<Question> findQuestionByQuizId(Long id) {
        Optional<Quiz> quiz = repo.findById(id);
        List<Question> questionList = new ArrayList<>();
        if(quiz.isPresent())
        {
            questionList = quiz.get().getQuestions();
        }
        return questionList;
    }
}
