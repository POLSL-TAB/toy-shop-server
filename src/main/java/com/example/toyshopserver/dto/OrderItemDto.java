package com.example.toyshopserver.dto;

public record OrderItemDto(
    Long productId,
    Integer quantity
) {

}
