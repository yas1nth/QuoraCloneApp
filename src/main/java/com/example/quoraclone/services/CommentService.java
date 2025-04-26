package com.example.quoraclone.services;

import com.example.quoraclone.dto.CommentDTO;
import com.example.quoraclone.models.Answer;
import com.example.quoraclone.models.Comment;
import com.example.quoraclone.repositories.AnswerRepository;
import com.example.quoraclone.repositories.CommentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  @Autowired
  private CommentRepository commentRepository;
  @Autowired private AnswerRepository answerRepository;

  // Add your service methods here
  public void deleteCommentById(Long id) {
    commentRepository.deleteById(id);
  }

  public Optional<Comment> getCommentById(Long id) {
    return commentRepository.findById(id);
  }

  public Optional<Comment> createComment(CommentDTO commentDTO){
    Comment comment = new Comment();
    comment.setContent(commentDTO.getContent());
    Optional<Answer> answer = answerRepository.findById(commentDTO.getAnswerId());
    answer.ifPresent(comment::setAnswer);
    if (commentDTO.getParentCommentId() != null) {
      Optional<Comment> parentComment = commentRepository.findById(commentDTO.getParentCommentId());
      parentComment.ifPresent(comment::setParentComment);
    }
    return Optional.of(commentRepository.save(comment));
  }

  public List<Comment> getRepliesByCommentId(Long commentId,int page,int size) {
    return commentRepository.findByParentCommentId(commentId, PageRequest.of(page,size)).getContent();
  }

  public List<Comment> getCommentsByAnswerId(Long answerId,int page,int size) {
    Pageable pageable = PageRequest.of(page, size);
    return commentRepository.findByAnswerId(answerId,pageable).getContent();
  }
}
