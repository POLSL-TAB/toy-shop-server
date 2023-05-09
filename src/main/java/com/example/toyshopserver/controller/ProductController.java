package com.example.toyshopserver.controller;

import com.example.toyshopserver.dto.ImageDto;
import com.example.toyshopserver.dto.ProductDto;
import com.example.toyshopserver.service.ProductService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping("/all")
  public List<ProductDto> getProducts() {
    return productService.getAll();
  }

  @GetMapping("/get")
  public Optional<ProductDto> getProduct(@RequestParam Long id) {
    return productService.getDtoById(id);
  }

  @GetMapping("/images/all")
  public List<ImageDto> getAllImages() {
    return productService.getAllProductImages();
  }

  @GetMapping("/images")
  public List<ImageDto> getProductImages(@RequestParam Long productId) {
    return productService.getProductImages(productId);
  }
}
