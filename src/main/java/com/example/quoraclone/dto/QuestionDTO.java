package com.example.quoraclone.dto;

import java.util.Set;
import lombok.Data;

@Data
public class QuestionDTO {

  private Long Id;
  private String title;
  private String content;
  private Long userId;
  private Set<Long> tagIds;
}
