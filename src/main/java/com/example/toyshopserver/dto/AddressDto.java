package com.example.toyshopserver.dto;

public record AddressDto(
    String phone,
    String postCode,
    String city,
    String street,
    String houseNumber,
    String apartmentNumber
) {

}
