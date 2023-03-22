package com.example.toyshopserver.service;

import com.example.toyshopserver.model.User;
import com.example.toyshopserver.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public Optional<User> getByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public boolean deleteByEmail(String email) {
    return userRepository.findByEmail(email)
        .map(user -> {
          userRepository.delete(user);
          return true;
        })
        .orElse(false);
  }
}
