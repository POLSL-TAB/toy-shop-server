package com.example.toyshopserver.service;

import com.example.toyshopserver.dto.CartItemDto;
import com.example.toyshopserver.model.CartItem;
import com.example.toyshopserver.model.Product;
import com.example.toyshopserver.model.User;
import com.example.toyshopserver.repository.CartRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

  private final CartRepository cartRepository;
  private final UserService userService;
  private final ProductService productService;

  public List<CartItemDto> getCartItems(String userEmail) {
    User user = userService.getByEmail(userEmail)
        .orElseThrow(() -> new UsernameNotFoundException(userEmail + " user not found"));
    return cartRepository.findAllByUser(user).stream()
        .map(this::mapCartItemToDto)
        .toList();
  }

  public void addCartItem(String userEmail, CartItemDto cartItemDto) {
    User user = userService.getByEmail(userEmail)
        .orElseThrow(() -> new UsernameNotFoundException(userEmail + " user not found"));
    Product product = productService.getById(cartItemDto.productId())
        .orElseThrow(() -> new IllegalArgumentException(cartItemDto.productId() + " product not found"));
    cartRepository.findFirstByUserAndProduct(user, product)
        .ifPresentOrElse(ci -> updateCartItemQuantity(ci, cartItemDto),
            () -> saveNewCartItem(user, cartItemDto));
  }

  public void deleteCartItem(String userEmail, Long id) {
    User user = userService.getByEmail(userEmail)
        .orElseThrow(() -> new UsernameNotFoundException(userEmail + " user not found"));
    Product product = productService.getById(id)
        .orElseThrow(() -> new IllegalArgumentException(id + " product not found"));
    cartRepository.deleteByUserAndProduct(user, product);
  }

  private void updateCartItemQuantity(CartItem cartItem, CartItemDto cartItemDto) {
    cartItem.setQuantity(cartItem.getQuantity() + cartItemDto.quantity());
    cartRepository.save(cartItem);
  }

  private void saveNewCartItem(User user, CartItemDto cartItemDto) {
    CartItem cartItem = mapDtoToCartItem(cartItemDto, user);
    cartRepository.save(cartItem);
  }

  private CartItemDto mapCartItemToDto(CartItem cartItem) {
    return new CartItemDto(cartItem.getProduct().getId(), cartItem.getQuantity());
  }

  private CartItem mapDtoToCartItem(CartItemDto cartItemDto, User user) {
    Product product = productService.getById(cartItemDto.productId())
        .orElseThrow(() -> new IllegalArgumentException(cartItemDto.productId() + " product not found"));
    CartItem cartItem = new CartItem();
    cartItem.setUser(user);
    cartItem.setProduct(product);
    cartItem.setQuantity(cartItemDto.quantity());
    return cartItem;
  }
}
