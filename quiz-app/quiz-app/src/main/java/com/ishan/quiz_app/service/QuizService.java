package com.ishan.quiz_app.service;

import com.ishan.quiz_app.model.Question;
import com.ishan.quiz_app.dao.QuestionDAO;
import com.ishan.quiz_app.dao.QuizDAO;
import com.ishan.quiz_app.model.QuestionWrapper;
import com.ishan.quiz_app.model.Quiz;
import com.ishan.quiz_app.model.UserResponse;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log
@Service
public class QuizService {
    @Autowired
    QuizDAO quizDAO;
    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<String> createQuiz(String category, int num, String title) {

        List<Question> questions = questionDAO.findRandomQuestionsByCategory(category, num);

        Quiz quiz = new Quiz();
        quiz.setId(num);
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);

        return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);


    }
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById ( int id){
        Optional<Quiz> quizFromDB = quizDAO.findById(id);
        List<Question> quizQuestionsFromDB = quizFromDB.get().getQuestions();
        List<QuestionWrapper> quizQuestionsForUser = new ArrayList<>();
        for (Question q : quizQuestionsFromDB) {

            QuestionWrapper quizQuestionForUser = new QuestionWrapper(q.getId(), q.getQuestion(), q.getOption1(), q.getOption2(), q.getOption3(), q.getDifficulty());
            quizQuestionsForUser.add(quizQuestionForUser);
        }

        return new ResponseEntity<>(quizQuestionsForUser, HttpStatus.OK);


    }

    Question getQuestionById(List<Question> list, int id){
        for(Question q: list){
            if(q.getId() == id)
                return q;
        }
        return null;
    }

    public ResponseEntity<Integer> getScore(int id, @RequestBody List<UserResponse> response) {

        Optional<Quiz> quiz = quizDAO.findById(id);
        List<Question> quizQuestions = quiz.get().getQuestions();
        int score = 0;

        for(UserResponse ur: response){

            if(ur.getResponse().equals(getQuestionById(quizQuestions,ur.getId()).getAnswer())) {
                score++;

            }
        }

        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}

