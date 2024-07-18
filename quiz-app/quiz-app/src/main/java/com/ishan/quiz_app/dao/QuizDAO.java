package com.ishan.quiz_app.dao;

import com.ishan.quiz_app.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDAO extends JpaRepository<Quiz, Integer> {
}
