package com.example.toyshopserver.service;

import com.example.toyshopserver.model.Product;
import com.example.toyshopserver.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public List<Product> getAll() {
    return productRepository.findAll();
  }

  public Optional<Product> getById(Long id) {
    return productRepository.findById(id);
  }
}
