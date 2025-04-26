package com.example.quoraclone.services;

import com.example.quoraclone.dto.TagDTO;
import com.example.quoraclone.models.Tag;
import com.example.quoraclone.repositories.TagRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

  @Autowired
  private TagRepository tagRepository;

  public List<Tag> getAllTags(){
    return tagRepository.findAll();
  }

  public Tag createTag(TagDTO tagDTO){
    Tag tag = new Tag();
    tag.setName(tagDTO.getName());
    return tagRepository.save(tag);
  }

  public void deleteTag(Long id){
    tagRepository.deleteById(id);
  }

  public Optional<Tag> getTagById(Long id){
    return tagRepository.findById(id);
  }

}
