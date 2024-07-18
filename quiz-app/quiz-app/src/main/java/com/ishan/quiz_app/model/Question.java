package com.ishan.quiz_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String option1;

    private String option2;

    private String option3;

    private String question;
    private String answer;
    private String category;
    @Column(name = "difficulty level")

    private String difficulty;


}
