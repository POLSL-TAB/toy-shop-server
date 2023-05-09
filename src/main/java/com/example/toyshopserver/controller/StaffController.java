package com.example.toyshopserver.controller;

import com.example.toyshopserver.dto.ImageDto;
import com.example.toyshopserver.dto.ProductDto;
import com.example.toyshopserver.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {

  private final ProductService productService;

  @PostMapping("/products/add")
  public ResponseEntity<Void> addProduct(@RequestBody ProductDto productDto) {
    productService.addProduct(productDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/products/update")
  public ResponseEntity<Void> updateProduct(@RequestBody ProductDto productDto) {
    productService.updateProduct(productDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/products/images/add")
  public ResponseEntity<Void> addProductImage(@RequestBody ImageDto productDto) {
    productService.addProductImage(productDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/products/images/delete")
  public ResponseEntity<Void> deleteProductImage(@RequestParam Long id) {
    productService.deleteProductImage(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
