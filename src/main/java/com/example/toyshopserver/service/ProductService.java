package com.example.toyshopserver.service;

import com.example.toyshopserver.dto.ImageDto;
import com.example.toyshopserver.dto.ProductDto;
import com.example.toyshopserver.model.Picture;
import com.example.toyshopserver.model.Product;
import com.example.toyshopserver.repository.PictureRepository;
import com.example.toyshopserver.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final PictureRepository pictureRepository;

  public List<ProductDto> getAll() {
    return productRepository.findAll().stream()
        .map(this::mapProductToDto)
        .toList();
  }

  public Optional<Product> getById(Long id) {
    return productRepository.findById(id);
  }

  public Optional<ProductDto> getDtoById(Long id) {
    return productRepository.findById(id)
        .map(this::mapProductToDto);
  }

  public void addProduct(ProductDto productDto) {
    Product product = mapDtoToProduct(productDto);
    product.setId(null);
    productRepository.save(product);
  }

  public void updateProduct(ProductDto productDto) {
    productRepository.save(mapDtoToProduct(productDto));
  }

  public void decreaseStock(long productId, int quantity) {
    productRepository.findById(productId)
        .ifPresent(product -> {
          product.setStock(product.getStock() + quantity);
          productRepository.save(product);
        });
  }

  public List<ImageDto> getAllProductImages() {
    return pictureRepository.findAll().stream()
        .map(this::mapImageToDto)
        .toList();
  }

  public List<ImageDto> getProductImages(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow();
    return pictureRepository.findAllByProduct(product).stream()
        .map(this::mapImageToDto)
        .toList();
  }

  public void addProductImage(ImageDto imageDto) {
    Product product = productRepository.findById(imageDto.productId())
        .orElseThrow();
    Picture picture = mapDtoToPicture(imageDto, product);
    product.getPictures().add(picture);
    productRepository.save(product);
  }

  public void deleteProductImage(Long id) {
    pictureRepository.deleteById(id);
  }

  private ProductDto mapProductToDto(Product product) {
    String price = Optional.ofNullable(product.getPrice())
        .map(BigDecimal::toPlainString)
        .orElse(null);
    return new ProductDto(product.getId(), product.getName(), product.getDescription(), price, product.getStock());
  }

  private Product mapDtoToProduct(ProductDto productDto) {
    Product product = new Product();
    product.setId(productDto.id());
    product.setName(productDto.name());
    product.setDescription(productDto.description());
    product.setPrice(new BigDecimal(productDto.price()));
    product.setStock(productDto.stock());
    return product;
  }

  private ImageDto mapImageToDto(Picture picture) {
    String dataB64 = Base64.getEncoder().encodeToString(picture.getData());
    return new ImageDto(picture.getId(), picture.getProduct().getId(), dataB64);
  }

  private Picture mapDtoToPicture(ImageDto imageDto, Product product) {
    Picture picture = new Picture();
    picture.setProduct(product);
    byte[] data = Base64.getDecoder().decode(imageDto.pictureB64());
    picture.setData(data);
    return picture;
  }
}
