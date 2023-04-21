package com.example.toyshopserver.controller;

import com.example.toyshopserver.dto.CartItemDto;
import com.example.toyshopserver.service.CartService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @GetMapping("/items")
  public List<CartItemDto> getCartItems(Principal principal) {
    return cartService.getCartItems(principal.getName());
  }

  @PostMapping("/add")
  public ResponseEntity<Void> addCartItem(Principal principal, @RequestBody CartItemDto cartItemDto) {
    cartService.addCartItem(principal.getName(), cartItemDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/delete")
  public ResponseEntity<Void> deletedCartItem(Principal principal, @RequestParam Long id) {
    cartService.deleteCartItem(principal.getName(), id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
