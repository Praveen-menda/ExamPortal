package com.exam.repo;

import com.exam.model.Exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Long> {
    @Query("from Quiz where category_cid = ?1  ")
    List<Quiz> findQuizByCategoryId(@Param("id")Long id);

    Quiz findByQtitle(String qtitle);
}
