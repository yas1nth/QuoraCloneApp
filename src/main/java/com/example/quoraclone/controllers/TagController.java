package com.example.quoraclone.controllers;

import com.example.quoraclone.dto.TagDTO;
import com.example.quoraclone.models.Tag;
import com.example.quoraclone.services.TagService;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

  @Autowired
  private TagService tagService;

  @GetMapping
  public List<Tag> getAllTags(){
    return tagService.getAllTags();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Tag> getTagById(@PathVariable Long Id){
    Optional<Tag> tag = tagService.getTagById(Id);
    return tag.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
  }

  @PostMapping
  public Tag createTag(@RequestBody TagDTO tagDTO){
    return tagService.createTag(tagDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTag(Long Id){
    tagService.deleteTag(Id);
    return ResponseEntity.noContent().build();
  }
}
