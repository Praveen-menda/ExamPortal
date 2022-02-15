package com.exam.service.impl;

import com.exam.model.Exam.Category;
import com.exam.model.Exam.Question;
import com.exam.model.Exam.Quiz;
import com.exam.repo.QuestionRepo;
import com.exam.repo.QuizRepo;
import com.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepo repo;
    @Autowired
    private QuizRepo quizRepo;


    @Override
    public Question addQuestion(Question question, Long quizId) throws Exception {
        Optional<Quiz> quiz = quizRepo.findById(question.getQuizId());
        if(quiz.isEmpty())
        {
            throw new Exception("Quiz Id is incorrect");
        }
        question.setQuiz(quiz.get());
        return repo.save(question);
    }

    @Override
    public Question updateQueston(Question question) throws Exception {

            Optional<Quiz> quiz = Optional.of(quizRepo.getById(question.getQuizId()));
            if(quiz.isEmpty())
            {
                throw new Exception("Quiz Id is incorrect");
            }
            question.setQuiz(quiz.get());
            return repo.save(question);
    }

    @Override
    public void removeQuestion(Long id) {
        Optional<Question> ques = Optional.of(repo.getById(id));
        if(ques.isPresent())
            repo.delete(ques.get());
    }
}
