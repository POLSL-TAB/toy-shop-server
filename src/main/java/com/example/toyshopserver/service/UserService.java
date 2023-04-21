package com.example.toyshopserver.service;

import static java.util.Collections.emptyList;
import static java.util.function.Predicate.not;

import com.example.toyshopserver.dto.AddressDto;
import com.example.toyshopserver.dto.UserDto;
import com.example.toyshopserver.model.Address;
import com.example.toyshopserver.model.Role;
import com.example.toyshopserver.model.User;
import com.example.toyshopserver.repository.AddressRepository;
import com.example.toyshopserver.repository.RoleRepository;
import com.example.toyshopserver.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final AddressRepository addressRepository;
  private final RoleRepository roleRepository;

  public List<UserDto> getAll() {
    return userRepository.findAll().stream()
        .map(this::mapUserToDto)
        .toList();
  }

  public Optional<User> getByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public Optional<UserDto> getDtoByEmail(String email) {
    return userRepository.findByEmail(email)
        .map(this::mapUserToDto);
  }

  public boolean deleteByEmail(String email) {
    return userRepository.findByEmail(email)
        .map(user -> {
          userRepository.delete(user);
          return true;
        })
        .orElse(false);
  }

  public Optional<AddressDto> getUserAddress(String email) {
    return userRepository.findByEmail(email)
        .map(User::getAddress)
        .map(this::mapAddressToDto);
  }

  public List<String> getUserRoles(String email) {
    return userRepository.findByEmail(email)
        .map(User::getRoles)
        .map(roles -> roles.stream()
            .map(Role::getName)
            .map(s -> s.substring(5))
            .toList())
        .orElse(emptyList());
  }

  public Optional<String> setUserRoles(String email, List<String> roles) {
    Set<String> allRoles = Set.of("USER", "STAFF", "ADMIN");
    List<String> unknownRoles = roles.stream()
        .filter(not(allRoles::contains))
        .toList();
    if (unknownRoles.isEmpty()) {
      userRepository.findByEmail(email)
          .ifPresent(user -> updateUserRoles(user, roles));
      return Optional.empty();
    }
    return Optional.of("Unknown roles: " + unknownRoles + ". Available roles: " + allRoles);
  }

  private void updateUserRoles(User user, List<String> roles) {
    Set<Role> newRoles = roles.stream()
        .map(r -> "ROLE_" + r)
        .map(roleRepository::findByName)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toSet());
    user.setRoles(newRoles);
    userRepository.save(user);
  }

  private UserDto mapUserToDto(User user) {
    return new UserDto(user.getEmail(), user.getPassword(), user.getName(), user.getSurname());
  }

  private User mapDtoToUser(UserDto userDto) {
    User user = new User();
    user.setEmail(userDto.email());
    user.setPassword(userDto.password());
    user.setName(userDto.name());
    user.setSurname(userDto.surname());
    return user;
  }

  private AddressDto mapAddressToDto(Address address) {
    return new AddressDto(address.getPhone(), address.getPostCode(), address.getCity(), address.getStreet(), address.getHouseNumber(),
        address.getApartmentNumber());
  }

  private Address mapDtoToAddress(AddressDto addressDto) {
    Address address = new Address();
    address.setPhone(addressDto.phone());
    address.setPostCode(addressDto.postCode());
    address.setCity(addressDto.city());
    address.setStreet(addressDto.street());
    address.setHouseNumber(addressDto.houseNumber());
    address.setApartmentNumber(addressDto.apartmentNumber());
    return address;
  }
}
