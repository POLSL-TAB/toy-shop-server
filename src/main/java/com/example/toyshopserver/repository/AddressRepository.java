package com.example.toyshopserver.repository;

import com.example.toyshopserver.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
