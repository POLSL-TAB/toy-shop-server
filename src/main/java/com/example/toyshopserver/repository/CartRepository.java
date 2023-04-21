package com.example.toyshopserver.repository;

import com.example.toyshopserver.model.CartItem;
import com.example.toyshopserver.model.Product;
import com.example.toyshopserver.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {

  List<CartItem> findAllByUser(User user);

  Optional<CartItem> findFirstByUserAndProduct(User user, Product product);

  void deleteAllByUserAndProduct(User user, Product product);

  void deleteAllByUser(User user);
}
