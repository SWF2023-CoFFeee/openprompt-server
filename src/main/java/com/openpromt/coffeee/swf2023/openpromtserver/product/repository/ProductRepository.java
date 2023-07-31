package com.openpromt.coffeee.swf2023.openpromtserver.product.repository;

import com.openpromt.coffeee.swf2023.openpromtserver.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
