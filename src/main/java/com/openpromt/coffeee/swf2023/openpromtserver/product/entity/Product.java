package com.openpromt.coffeee.swf2023.openpromtserver.product.entity;

import com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity.Copyright;
import com.openpromt.coffeee.swf2023.openpromtserver.product.dto.GetProductDetailResponse;
import com.openpromt.coffeee.swf2023.openpromtserver.product.dto.GetProductListResponse;
import com.openpromt.coffeee.swf2023.openpromtserver.product.dto.RegistProductRequest;
import com.openpromt.coffeee.swf2023.openpromtserver.product.util.AIType;
import com.openpromt.coffeee.swf2023.openpromtserver.product.util.ProductType;
import com.openpromt.coffeee.swf2023.openpromtserver.product.util.ProductTypeConverter;
import com.openpromt.coffeee.swf2023.openpromtserver.user.util.AITypeConverter;
import com.openpromt.coffeee.swf2023.openpromtserver.util.auditing.BaseEntity;
import com.openpromt.coffeee.swf2023.openpromtserver.util.googlestorage.GoogleStorageUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @JoinColumn(name = "copyrightId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Copyright copyrightId;

    private String title;
    private String description;
    private String thumbnail;
    private String address;
    private Integer likes;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    private AIType AItype;

    private boolean status;

    public static GetProductListResponse productToListResponse(Product e) {
        return GetProductListResponse.builder()
                .thumbnail(e.getThumbnail())
                .product_title(e.getTitle())
                .like(e.getLikes())
                .username(e.copyrightId.getUser().getUsername())
                .price(e.getPrice())
                .AI_type(e.getAItype().getValue())
                .build();
    }
    public static GetProductDetailResponse productToDetailResponse(Product e){
        return GetProductDetailResponse.builder()
                .product_type(e.getProductType().getValue())
                .description(e.getDescription())
                .price(e.getPrice())
                .username(e.getCopyrightId().getUser().getUsername())
                .title(e.getTitle())
                .thumbnail(e.getThumbnail())
                .AI_type(e.getAItype().getValue())
                .build();
    }

    public static Product registProductRequestToProduct(RegistProductRequest request) throws IOException {
        return Product.builder()
                .address(request.getSeller_addr())
                .productType(ProductType.valueOf(request.getProduct_type()))
                .title(request.getProduct_title())
                .description(request.getDescription())
                .price(request.getPrice())
                .thumbnail(GoogleStorageUtil.getGoogleStorageUrl(request.getThumbnail())) // util 하나 작성
                .AItype(AIType.valueOf(request.getAI_type()))
                .build();
    }

    public void cancelSellingProduct(){
        this.status=false;
    }

    public void updateCopyright(Copyright copyright) {
        this.copyrightId = copyright;
    }
}
