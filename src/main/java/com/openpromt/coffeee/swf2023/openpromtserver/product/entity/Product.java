package com.openpromt.coffeee.swf2023.openpromtserver.product.entity;

import com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity.Copyright;
import com.openpromt.coffeee.swf2023.openpromtserver.product.dto.GetProductDetailResponse;
import com.openpromt.coffeee.swf2023.openpromtserver.product.dto.GetProductListResponse;
import com.openpromt.coffeee.swf2023.openpromtserver.product.util.AIType;
import com.openpromt.coffeee.swf2023.openpromtserver.product.util.ProductType;
import com.openpromt.coffeee.swf2023.openpromtserver.product.util.ProductTypeConverter;
import com.openpromt.coffeee.swf2023.openpromtserver.user.util.AITypeConverter;
import com.openpromt.coffeee.swf2023.openpromtserver.util.auditing.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copyright_id")
    private Copyright copyright;

    private String title;
    private String description;
    private String thumbnail;
    private String address;
    private Integer likes;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private ProductType product_type;

    @Enumerated(EnumType.STRING)
    private AIType AI_type;

    private boolean status;

    public static GetProductListResponse productToListResponse(Product e) {
        return GetProductListResponse.builder()
                .thumbnail(e.getThumbnail())
                .product_title(e.getTitle())
                .like(e.getLikes())
                .username(e.copyright.getUser().getUsername())
                .price(e.getPrice())
                .AI_type(e.getAI_type().getValue())
                .build();
    }
    public static GetProductDetailResponse productToDetailResponse(Product e){
        return GetProductDetailResponse.builder()
                .product_type(e.getProduct_type().getValue())
                .description(e.getDescription())
                .price(e.getPrice())
                .username(e.getCopyright().getUser().getUsername())
                .title(e.getTitle())
                .thumbnail(e.getThumbnail())
                .AI_type(e.getAI_type().getValue())
                .build();
    }

    public void cancelSellingProduct(){
        this.status=false;
    }
}
