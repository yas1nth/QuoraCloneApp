package com.example.quoraclone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.Data;

@Entity
@Data
public class User extends BaseModel{

  private String username;
  private String password;

  @ManyToMany
  @JoinTable(
      name = "user_tags",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private Set<Tag> followedTags;

}
