package com.exam.repo;

import com.exam.model.Exam.Question;
import com.exam.model.Exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {

    @Query("from Question where quiz_qid = ?1  ")
    List<Question> findQuestionByQuizId(@Param("id")Long id);
}
