package com.example.quoraclone.controllers;

import com.example.quoraclone.dto.UserDTO;
import com.example.quoraclone.models.Question;
import com.example.quoraclone.models.User;
import com.example.quoraclone.services.UserFeedService;
import com.example.quoraclone.services.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired private UserService userService;

  @Autowired private UserFeedService userFeedService;

  @GetMapping
  public List<User> getAllUsers(){
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long Id){
    Optional<User> user = userService.getUserById(Id);
    return user.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
  }

  @PostMapping
  public User createUser(@RequestBody UserDTO userDTO){
    return userService.createUser(userDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long Id){
    userService.deleteUser(Id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{userId}/followTag/{tagId}")
  public ResponseEntity<Void> followTag(@PathVariable Long userId,@PathVariable Long tagId){
    userService.followTag(userId,tagId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{userId}/feed")
  public List<Question> getUserFeed(@PathVariable Long userId, @RequestParam int page, @RequestParam int size) {
    return userFeedService.getUserFeed(userId, page, size);
  }

}
