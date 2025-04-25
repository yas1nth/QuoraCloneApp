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
public class Comment extends BaseModel{

  private String content;

  @ManyToOne
  @JoinColumn(name = "answer_id")
  private Answer answer;

  @ManyToOne
  @JoinColumn(name = "parent_comment_id")
  private Comment parentComment;

  @OneToMany(mappedBy = "parentComment")
  private Set<Comment> replies;

  @ManyToMany
  @JoinTable(
      name = "comment_likes",
      joinColumns = @JoinColumn(name = "comment_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private Set<User> likedBy;
}
