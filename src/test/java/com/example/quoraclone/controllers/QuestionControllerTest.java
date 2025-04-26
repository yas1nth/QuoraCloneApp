package com.example.quoraclone.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import com.example.quoraclone.dto.QuestionDTO;
import com.example.quoraclone.models.Question;
import com.example.quoraclone.services.QuestionService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class QuestionControllerTest {

  @InjectMocks
  private QuestionController questionController;

  @Mock
  private QuestionService questionService;

  @BeforeEach
  public void setUp(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetQuestionById_Success() {
    long questionId = 1L;
    Question mockQuestion = Question.builder().build();
    mockQuestion.setId(questionId);

    //mocking
    when(questionService.getQuestionById(questionId)).thenReturn(Optional.of(mockQuestion));

    //preform the test
    ResponseEntity<Question> response =  questionController.getQuestionById(questionId);
    Question returnedQuestion = response.getBody();
    //assertions
    assertEquals(HttpStatus.OK,response.getStatusCode());
    assertEquals(questionId,returnedQuestion.getId());
  }

  @Test
  public void testGetQuestionById_NOT_FOUND() {
    long questionId = 1L;

    //mocking
    when(questionService.getQuestionById(questionId)).thenReturn(Optional.empty());

    //preform the test
    ResponseEntity<Question> response =  questionController.getQuestionById(questionId);
    //assertions
    assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
  }

  @Test
  public void testCreateQuestion_Success(){
    QuestionDTO questionDTO = QuestionDTO.builder().userId(1L).build();
    Question mockQuestion = Question.builder().build();
    mockQuestion.setId(1L);
    //mock
    when(questionService.createQuestion(questionDTO)).thenReturn(mockQuestion);
    //perform the test
    ResponseEntity<Question> response = questionController.createQuestion(questionDTO);
    //Assertions
    assertEquals(HttpStatus.CREATED,response.getStatusCode());
  }

}
