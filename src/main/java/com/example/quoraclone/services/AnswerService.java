package com.example.quoraclone.services;

import com.example.quoraclone.dto.AnswerDTO;
import com.example.quoraclone.models.Answer;
import com.example.quoraclone.models.Question;
import com.example.quoraclone.models.User;
import com.example.quoraclone.repositories.AnswerRepository;
import com.example.quoraclone.repositories.QuestionRepository;
import com.example.quoraclone.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

  @Autowired
  private AnswerRepository answerRepository;
  @Autowired private UserRepository userRepository;
  @Autowired private QuestionRepository questionRepository;

  public void deleteAnswer(Long id) {
    answerRepository.deleteById(id);
  }

  public List<Answer> getAnswersByQuestionId(Long questionId,int page,int size) {
    Pageable pageable = PageRequest.of(page, size);
    List<Answer> answers= answerRepository.findByQuestionId(questionId,pageable).getContent();
    return answers;
  }

  public Answer createAnswer(AnswerDTO answerDTO) {
    Answer answer = new Answer();
    answer.setContent(answerDTO.getContent());

    Optional<User> userId = userRepository.findById(answerDTO.getUserId());
    userId.ifPresent(answer::setUser);

    Optional<Question> question = questionRepository.findById(answerDTO.getQuestionId());
    question.ifPresent(answer::setQuestion);

    return answerRepository.save(answer);
  }

  public Optional<Answer> getAnswerById(Long id) {
    return answerRepository.findById(id);
  }

}
