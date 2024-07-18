package com.ishan.quiz_app.service;

import com.ishan.quiz_app.model.Question;
import com.ishan.quiz_app.dao.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO dao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public List<Question> getQuestionsByCategory(String category) {
        return dao.findByCategory(category);
    }

    public ResponseEntity<String>  addQuestion(Question question) {
            dao.save(question);
        return new ResponseEntity<>( new String("success"), HttpStatus.CREATED);
    }

    public String deleteQuestion(Integer id) {
        dao.deleteById(id);
        return "deleted";
    }
}
