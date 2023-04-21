package com.example.toyshopserver.dto;

import java.util.List;

public record OrderDto(
    Long id,
    String userEmail,
    boolean paid,
    String created,
    String completed,
    String paymentType,
    List<OrderItemDto> oderItems
) {

}
