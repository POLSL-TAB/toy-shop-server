package com.example.toyshopserver.service;

import com.example.toyshopserver.dto.ProductDto;
import com.example.toyshopserver.model.Product;
import com.example.toyshopserver.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public List<ProductDto> getAll() {
    return productRepository.findAll().stream()
        .map(this::mapProductToDto)
        .toList();
  }

  public Optional<Product> getById(Long id) {
    return productRepository.findById(id);
  }

  private ProductDto mapProductToDto(Product product) {
    String price = Optional.ofNullable(product.getPrice())
        .map(BigDecimal::toPlainString)
        .orElse(null);
    return new ProductDto(product.getId(), product.getName(), product.getDescription(), price, product.getStock());
  }
}
