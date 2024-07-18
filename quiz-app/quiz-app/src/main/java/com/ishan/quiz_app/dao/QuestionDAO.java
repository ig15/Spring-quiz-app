package com.ishan.quiz_app.dao;

import com.ishan.quiz_app.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {

    public List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM questions q WHERE q.category=:category ORDER BY RANDOM() LIMIT :num", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int num);
}
