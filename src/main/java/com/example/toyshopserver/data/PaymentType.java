package com.example.toyshopserver.data;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentType {
  CARD("Karta"),
  TRANSFER("Przelew"),
  COD("Za pobraniem");

  private final String value;
}
