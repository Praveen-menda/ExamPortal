package com.exam.model.Exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "quiz")
@NoArgsConstructor
@Getter
@Setter
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qid;
    private String qtitle;
    private String qdescription;
    private String maxMarks;
    private String noOfQuestion;
    @Transient
    private Long catId;
    private boolean active = false;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "quiz",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();


    public Quiz(String qtitle, String qdescription, String maxMarks, String noOfQuestion, boolean active) {
        this.qtitle = qtitle;
        this.qdescription = qdescription;
        this.maxMarks = maxMarks;
        this.noOfQuestion = noOfQuestion;
        this.active = active;
    }
}
