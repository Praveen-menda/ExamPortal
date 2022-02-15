package com.exam.service;

import com.exam.model.Exam.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {


    List<Quiz> findQuizById(Long id);

    Quiz addQuiz(Quiz quiz, Long id) throws Exception;

    Quiz updateQuiz(Quiz quiz) throws Exception;

    void removeQuiz(Long id);
}
