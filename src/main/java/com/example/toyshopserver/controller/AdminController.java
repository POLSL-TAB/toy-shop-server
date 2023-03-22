package com.example.toyshopserver.controller;

import com.example.toyshopserver.model.User;
import com.example.toyshopserver.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

  private final UserService userService;

  @GetMapping("/get_usrers")
  public List<User> getAllUsers() {
    return userService.getAll();
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteUserByEmail(@RequestBody String email) {
    boolean deleted = userService.deleteByEmail(email);
    if (deleted) {
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
}
