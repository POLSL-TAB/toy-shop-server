package com.example.toyshopserver.controller;

import com.example.toyshopserver.model.Role;
import com.example.toyshopserver.model.User;
import com.example.toyshopserver.dto.UserDto;
import com.example.toyshopserver.repository.RoleRepository;
import com.example.toyshopserver.repository.UserRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/signin")
  public ResponseEntity<String> authenticateUser(@RequestBody UserDto userDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(userDto.email(), userDto.password()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
  }

  @PostMapping("/signup")
  public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {

    if (userRepository.findByEmail(userDto.email()).isPresent()) {
      return new ResponseEntity<>("Email is already registered!", HttpStatus.BAD_REQUEST);
    }

    User user = new User();
    user.setEmail(userDto.email());
    user.setPassword(passwordEncoder.encode(userDto.password()));

    Role roles = roleRepository.findByName("ROLE_USER")
        .orElseThrow();
    user.setRoles(Collections.singleton(roles));

    userRepository.save(user);

    return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
  }
}
