package com.example.toyshopserver.repository;

import com.example.toyshopserver.model.Complaint;
import com.example.toyshopserver.model.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

  Optional<Complaint> findByOrder(Order order);
}
