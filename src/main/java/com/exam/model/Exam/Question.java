package com.exam.model.Exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qid;
    @Column(length = 1000)
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    @Transient
    private Long quizId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Quiz quiz;
}
