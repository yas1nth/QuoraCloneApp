package com.example.quoraclone.controllers;

import com.example.quoraclone.dto.QuestionDTO;
import com.example.quoraclone.models.Question;
import com.example.quoraclone.services.QuestionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

  @Autowired private QuestionService questionService;

  @GetMapping("/{id}")
  public ResponseEntity<Question> getQuestionById(@PathVariable Long Id){
     Optional<Question> question = questionService.getQuestionById(Id);
     return question.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Question> createQuestion(@RequestBody QuestionDTO questionDTO){
    Question question = questionService.createQuestion(questionDTO);
    return new ResponseEntity<>(question,HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public void deleteQuestion(@PathVariable Long Id){
    questionService.deleteQuestion(Id);
  }

  @GetMapping
  public List<Question> getAllQuestions(@RequestParam int page,@RequestParam int size){
    return questionService.getAllQuestions(page,size);
  }
}
