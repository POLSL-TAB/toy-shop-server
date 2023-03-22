package com.example.toyshopserver.dto;

public record CartItemDto(
    Long productId,
    Integer quantity
) {

}
