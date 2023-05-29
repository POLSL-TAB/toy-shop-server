package com.example.toyshopserver.data;

import java.util.Arrays;

public enum ComplaintStatus {
  CREATED,
  SENT_TO_MANUFACTURER,
  REJECTED_BY_SELLER,
  REJECTED_BY_MANUFACTURER,
  WAITING_FOR_DELIVERY,
  COMPLETED;

  public static ComplaintStatus fromName(String name) {
    return Arrays.stream(values())
        .filter(value -> value.name().equals(name))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Wrong complaint status: " + name));
  }
}
