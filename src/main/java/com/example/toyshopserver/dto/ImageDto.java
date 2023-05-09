package com.example.toyshopserver.dto;

public record ImageDto(
    Long id,
    Long productId,
    String pictureB64
) {

}
