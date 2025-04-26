package com.example.quoraclone.dto;

import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionDTO {

  private Long Id;
  private String title;
  private String content;
  private Long userId;
  private Set<Long> tagIds;
}
