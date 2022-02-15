package com.exam.service;

import com.exam.model.Exam.Question;
import com.exam.model.Exam.Quiz;

import java.util.List;

public interface QuestionService {


    Question addQuestion(Question question, Long quizId) throws Exception;

    Question updateQueston(Question question) throws Exception;

    void removeQuestion(Long id);
}
