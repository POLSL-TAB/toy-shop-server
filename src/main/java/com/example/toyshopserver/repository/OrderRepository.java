package com.example.toyshopserver.repository;

import com.example.toyshopserver.model.Order;
import com.example.toyshopserver.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> findAllByUser(User user);
}
