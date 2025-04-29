package com.example.quoraclone.services;

import com.example.quoraclone.dto.UserDTO;
import com.example.quoraclone.models.Tag;
import com.example.quoraclone.models.User;
import com.example.quoraclone.repositories.TagRepository;
import com.example.quoraclone.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TagRepository tagRepository;

  // Add your service methods here
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  public User createUser(UserDTO userDTO) {
    User user = new User();
    user.setUsername(userDTO.getUsername());
    user.setPassword(userDTO.getPassword());
    return userRepository.save(user);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  public void followTag(Long userId,Long tagId){
    User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not Found"));
    Tag tag = tagRepository.findById(tagId).orElseThrow(()-> new RuntimeException("Tag not Found"));
    user.getFollowedTags().add(tag);
    userRepository.save(user);
  }

  public void testCodeForAuthorChange(){

  }

}
