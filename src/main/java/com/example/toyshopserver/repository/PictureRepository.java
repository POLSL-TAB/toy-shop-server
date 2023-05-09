package com.example.toyshopserver.repository;

import com.example.toyshopserver.model.Picture;
import com.example.toyshopserver.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {

  List<Picture> findAllByProduct(Product product);
}
