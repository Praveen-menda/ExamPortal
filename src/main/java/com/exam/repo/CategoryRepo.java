package com.exam.repo;

import com.exam.model.Exam.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

   // @Query("From category where cname = 1?")
    public Category findByCname( String cname);
}
