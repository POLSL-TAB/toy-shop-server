package com.example.toyshopserver.controller;

import com.example.toyshopserver.model.Product;
import com.example.toyshopserver.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping("/all")
  public List<Product> getProducts() {
    return productService.getAll();
  }
}
