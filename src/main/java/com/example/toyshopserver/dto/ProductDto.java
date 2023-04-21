package com.example.toyshopserver.dto;

public record ProductDto(
    Long id,
    String name,
    String description,
    String price,
    Integer stock
) {

}
