package com.openpromt.coffeee.swf2023.openpromtserver.product.entity;

import com.openpromt.coffeee.swf2023.openpromtserver.product.util.AIType;
import com.openpromt.coffeee.swf2023.openpromtserver.product.util.ProductType;
import com.openpromt.coffeee.swf2023.openpromtserver.product.util.ProductTypeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long product_id;
    private Long copyright_id;
    private String title;
    private String description;
    private String thumbnail;
    private String account;
    private int like;
    @Convert(converter = ProductTypeConverter.class)
    private ProductType product_type;
    private AIType AI_type;
    private boolean status;
}
