package com.example.toyshopserver.dto;

public record UserDto(
    String email,
    String password,
    String name,
    String surname,
    String street,
    String number,
    String city,
    String postCode
) {

}
