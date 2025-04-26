package com.example.quoraclone.controllers;

import com.example.quoraclone.dto.AnswerDTO;
import com.example.quoraclone.models.Answer;
import com.example.quoraclone.services.AnswerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/answers")
public class AnswerController {

  @Autowired private AnswerService answerService;

  @GetMapping("/{id}")
  public ResponseEntity<Answer> getAnswerById(@PathVariable Long Id){
    Optional<Answer> answer = answerService.getAnswerById(Id);
    return answer.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAnswer(@PathVariable Long Id){
    answerService.deleteAnswer(Id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public Answer createAnswer(@RequestBody AnswerDTO answerDTO){
    return answerService.createAnswer(answerDTO);
  }

  @GetMapping("/question/{questionId}")
  public List<Answer> getAllAnswersByQuestionId(@PathVariable Long questionId,@RequestParam int page,@RequestParam int size){
    List<Answer> answers = answerService.getAnswersByQuestionId(questionId,page,size);
    return answers;
  }

}
