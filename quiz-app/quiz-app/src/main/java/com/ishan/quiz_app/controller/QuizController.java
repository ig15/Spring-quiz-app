package com.ishan.quiz_app.controller;

import ch.qos.logback.core.model.INamedModel;
import com.ishan.quiz_app.model.Question;
import com.ishan.quiz_app.model.QuestionWrapper;
import com.ishan.quiz_app.model.UserResponse;
import com.ishan.quiz_app.service.QuizService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> create(@RequestParam String category, @RequestParam(name = "numQuestions") int num, @RequestParam String title ){
        return quizService.createQuiz( category,  num,  title );
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
        return quizService.getQuizQuestionsById(id);
    }

    @PostMapping ("submit/{id}")
    public ResponseEntity<Integer, String > getScore(@PathVariable int id, @RequestBody List<UserResponse> response){
        ResponseEntity<Integer> score = quizService.getScore(id, response);
        if(response.size() == score.getBody() ) {
            System.out.println("Congrats you are a top scorer!");
        }
        return new ResponseEntity<>(quizService.getScore(id, response), "Congrats you are a top scorer!", HttpStatus.OK);
    }


}
