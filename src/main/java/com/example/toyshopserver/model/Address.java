package com.example.toyshopserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "addresses")
public class Address {

  private static final String NOT_BLANK_MESSAGE = "Pole nie może być puste";
  private static final String PATTERN_NOT_MATCH_MESSAGE = "Niepoprawny format";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private User user;

  @NotBlank(message = NOT_BLANK_MESSAGE)
  @Pattern(regexp = "^(?:\\+\\d{2}-?)?\\d{9}$", message = PATTERN_NOT_MATCH_MESSAGE)
  private String phone;

  @Column(name = "post_code")
  @NotBlank(message = NOT_BLANK_MESSAGE)
  @Pattern(regexp = "^\\d{2}-\\d{3}$", message = PATTERN_NOT_MATCH_MESSAGE)
  private String postCode;

  @NotBlank(message = NOT_BLANK_MESSAGE)
  private String city;

  @NotBlank(message = NOT_BLANK_MESSAGE)
  private String street;

  @Column(name = "house_number")
  @NotBlank(message = NOT_BLANK_MESSAGE)
  @Pattern(regexp = "^\\d+[a-zA-Z]?$", message = PATTERN_NOT_MATCH_MESSAGE)
  private String houseNumber;

  @Pattern(regexp = "^\\d*$", message = PATTERN_NOT_MATCH_MESSAGE)
  @Column(name = "apartment_number")
  private String apartmentNumber;
}
