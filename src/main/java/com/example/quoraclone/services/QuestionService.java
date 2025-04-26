package com.example.quoraclone.services;

import com.example.quoraclone.dto.QuestionDTO;
import com.example.quoraclone.models.Question;
import com.example.quoraclone.models.Tag;
import com.example.quoraclone.models.User;
import com.example.quoraclone.repositories.QuestionRepository;
import com.example.quoraclone.repositories.TagRepository;
import com.example.quoraclone.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private TagRepository tagRepository;

  @Autowired
  private UserRepository userRepository;

  public Optional<Question> getQuestionById(Long id) {
    return questionRepository.findById(id);
  }

  public Question createQuestion(QuestionDTO questionDTO) {
    Question question = new Question();
    question.setTitle(questionDTO.getTitle());
    question.setContent(question.getContent());

    Optional<User> userId = userRepository.findById(questionDTO.getUserId());
    userId.ifPresent(question::setUser);

    Set<Tag> tags = questionDTO.getTagIds().stream()
        .map(tagId -> tagRepository.findById(tagId).orElse(null))
        .filter(tag -> tag != null)
        .collect(Collectors.toSet());
    question.setTags(tags);

    return questionRepository.save(question);
  }

  public void deleteQuestion(Long id) {
    questionRepository.deleteById(id);
  }

  public List<Question> getAllQuestions(int page,int size){
    Pageable pageable = PageRequest.of(page,size);
    return questionRepository.findAll(pageable).getContent();
  }

}
