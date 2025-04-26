package com.example.quoraclone.services;

import com.example.quoraclone.models.Question;
import com.example.quoraclone.models.Tag;
import com.example.quoraclone.models.User;
import com.example.quoraclone.repositories.QuestionRepository;
import com.example.quoraclone.repositories.UserRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserFeedService {

  @Autowired private UserRepository userRepository;
  @Autowired private QuestionRepository questionRepository;

  public List<Question> getUserFeed(Long userId, int page, int size) {
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

    Set<Long> tagIds = user.getFollowedTags().stream().map(Tag::getId).collect(Collectors.toSet());
    return questionRepository.findQuestionByTags(tagIds, PageRequest.of(page, size)).getContent();
  }

}
