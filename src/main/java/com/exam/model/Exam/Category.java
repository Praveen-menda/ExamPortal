package com.exam.model.Exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
@NoArgsConstructor
@Getter
@Setter
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cid;
    private String cname;
    private String cdescription;

    @JsonIgnore
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Quiz> quizzes = new ArrayList<>();
    public Category(String cname, String cdescription) {
        this.cname = cname;
        this.cdescription = cdescription;
    }
}
