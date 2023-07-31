package com.openpromt.coffeee.swf2023.openpromtserver.product.entity;

import com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity.Copyright;
import com.openpromt.coffeee.swf2023.openpromtserver.product.util.AIType;
import com.openpromt.coffeee.swf2023.openpromtserver.product.util.ProductType;
import com.openpromt.coffeee.swf2023.openpromtserver.product.util.ProductTypeConverter;
import com.openpromt.coffeee.swf2023.openpromtserver.user.util.AITypeConverter;
import com.openpromt.coffeee.swf2023.openpromtserver.util.auditing.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long product_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copyright_id")
    private Copyright copyright_id;

    private String title;
    private String description;
    private String thumbnail;
    private String address;
    private Integer likes;

    @Enumerated(EnumType.STRING)
    private ProductType product_type;

    @Enumerated(EnumType.STRING)
    private AIType AI_type;

    private boolean status;
}
