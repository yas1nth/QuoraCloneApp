package com.example.quoraclone.dto;

import java.util.Set;
import lombok.Data;

@Data
public class AnswerDTO {

  private Long Id;
  private String content;
  private Long userId;
  private Long questionId;
}
