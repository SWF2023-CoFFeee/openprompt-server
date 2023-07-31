package com.openpromt.coffeee.swf2023.openpromtserver.product.repository;

import com.openpromt.coffeee.swf2023.openpromtserver.product.dto.GetProductDetailResponse;
import com.openpromt.coffeee.swf2023.openpromtserver.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, QueryDslProductRepository {
    List<Product> findAllByProductType(String product_type);

    List<Product> findByCopyright_Id(Long copyright_id);

//    Product findByProductId(String product_id);
}
