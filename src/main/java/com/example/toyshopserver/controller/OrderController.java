package com.example.toyshopserver.controller;

import com.example.toyshopserver.dto.OrderDto;
import com.example.toyshopserver.service.OrderService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping("/all")
  public List<OrderDto> getOrders(Principal principal) {
    return orderService.getAllOrders(principal.getName());
  }

  @PutMapping("/create")
  public ResponseEntity<String> createOrder(Principal principal) {
    try {
      orderService.createOrder(principal.getName());
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
