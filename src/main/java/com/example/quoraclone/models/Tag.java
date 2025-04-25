package com.example.quoraclone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.Data;

@Data
@Entity
public class Tag extends BaseModel{

  private String name;

  @ManyToMany(mappedBy = "followedTags")
  private Set<User> followers;
}
