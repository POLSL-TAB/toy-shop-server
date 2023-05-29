package com.example.toyshopserver.dto;

public record ComplaintDTO(
    Long id,
    Long orderId,
    String status,
    String reason,
    String created,
    String updated
) {

}
