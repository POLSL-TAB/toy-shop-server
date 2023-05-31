package com.example.toyshopserver.model;

import com.example.toyshopserver.data.PaymentType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems;

  @ManyToOne
  private User user;

  private BigDecimal amount;

  private boolean paid;

  private LocalDateTime created;

  private LocalDateTime completed;

  @Column(name = "payment_type")
  private Enum<PaymentType> paymentType;

  @OneToOne(mappedBy = "order")
  private Complaint complaint;

  private boolean returned;
}
