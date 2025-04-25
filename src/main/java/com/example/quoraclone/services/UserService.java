package com.example.quoraclone.services;

import com.example.quoraclone.dto.UserDTO;
import com.example.quoraclone.models.User;
import com.example.quoraclone.repositories.TagRepository;
import com.example.quoraclone.repositories.UserRepository;
import java.util.List;
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

  public User getUserById(Long id) {
    return userRepository.findById(id).orElse(null);
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

}
