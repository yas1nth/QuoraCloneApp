package com.example.quoraclone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Data;

@Entity
@Data
public class Answer extends BaseModel{

  private String content;

  @ManyToOne
  @JoinColumn(name = "question_id")
  private Question question;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "answer")
  private Set<Comment> comments;

  @ManyToMany
  @JoinTable(
      name = "answer_likes",
      joinColumns = @JoinColumn(name = "answer_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private Set<User> likedBy;

}
