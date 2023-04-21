package com.example.toyshopserver.controller;

import com.example.toyshopserver.dto.UserDto;
import com.example.toyshopserver.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

  private final UserService userService;

  @GetMapping("/users/all")
  public List<UserDto> getAllUsers() {
    return userService.getAll();
  }

  @GetMapping("/users/get")
  public Optional<UserDto> getByEmail(@RequestParam String email) {
    return userService.getDtoByEmail(email);
  }

  @GetMapping("/users/role")
  public List<String> getRole(@RequestParam String email) {
    return userService.getUserRoles(email);
  }

  @PostMapping("/users/role")
  public ResponseEntity<String> modifyRoles(@RequestParam String email, @RequestParam List<String> roles) {
    return userService.setUserRoles(email, roles)
        .map(s -> new ResponseEntity<>(s, HttpStatus.BAD_REQUEST))
        .orElse(new ResponseEntity<>(HttpStatus.OK));
  }

  @DeleteMapping("/users/delete")
  public ResponseEntity<String> deleteUserByEmail(@RequestParam String email) {
    boolean deleted = userService.deleteByEmail(email);
    if (deleted) {
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
  }
}
