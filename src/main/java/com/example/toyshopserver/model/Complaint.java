package com.example.toyshopserver.model;

import com.example.toyshopserver.data.ComplaintStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "complaints")
public class Complaint {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private Order order;

  private Enum<ComplaintStatus> status;

  private String reason;

  private LocalDateTime created;

  private LocalDateTime updated;
}
