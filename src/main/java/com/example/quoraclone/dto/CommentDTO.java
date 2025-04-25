package com.example.quoraclone.dto;

import lombok.Data;

@Data
public class CommentDTO {

  private Long Id;
  private String content;
  private Long answerId;
  private Long parentCommentId;
}
