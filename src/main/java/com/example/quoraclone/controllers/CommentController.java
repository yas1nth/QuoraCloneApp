package com.example.quoraclone.controllers;

import com.example.quoraclone.dto.CommentDTO;
import com.example.quoraclone.models.Comment;
import com.example.quoraclone.services.CommentService;
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
@RequestMapping("/api/v1/comments")
public class CommentController {

  @Autowired private CommentService commentService;

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCommentById(@PathVariable Long Id){
    commentService.deleteCommentById(Id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("{id}")
  public ResponseEntity<Comment> getCommentById(@PathVariable Long Id){
    Optional<Comment> comment = commentService.getCommentById(Id);
    return comment.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
  }

  @PostMapping
  public Optional<Comment> createComment(@RequestBody CommentDTO commentDTO){
    return commentService.createComment(commentDTO);
  }

  @GetMapping("/answer/{answerId}")
  public List<Comment> getAllCommentsByAnswerId(@PathVariable Long answerId,@RequestParam int page,@RequestParam int size){
    return commentService.getCommentsByAnswerId(answerId,page,size);
  }

  @GetMapping("comment/{commentId}")
  public List<Comment> getAllCommentsByCommentId(@PathVariable Long commentId,@RequestParam int page,@RequestParam int size){
    return commentService.getRepliesByCommentId(commentId,page,size);
  }
}
