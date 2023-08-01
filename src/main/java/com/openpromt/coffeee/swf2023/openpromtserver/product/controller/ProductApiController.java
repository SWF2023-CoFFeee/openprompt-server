package com.openpromt.coffeee.swf2023.openpromtserver.product.controller;

import com.openpromt.coffeee.swf2023.openpromtserver.product.dto.GetProductDetailResponse;
import com.openpromt.coffeee.swf2023.openpromtserver.product.dto.GetProductListResponse;
import com.openpromt.coffeee.swf2023.openpromtserver.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;


@Slf4j
@RequestMapping("/api/v2/product")
@RequiredArgsConstructor
@RestController
public class ProductApiController {


    private final ProductService productService;

    @GetMapping("/")
    public List<GetProductListResponse> getProductListByProductType(@RequestParam("product_type") String product_type){

       return productService.getProductListByProductType(product_type);
    }

    @GetMapping("/{productId}")
    public GetProductDetailResponse getProductDetail(@PathVariable("productId") Long product_id){
        return productService.getProductDetail(product_id);
    }

    @PatchMapping("/copyright")
    public Long buyCopyright(Principal principal, @RequestParam Long product_id){
        return productService.buyCopyright(product_id,principal.getName());
    }

    @PostMapping("/ticket")
    public void buyTicket(Principal principal, @RequestParam Long product_id){
        productService.buyTicket(product_id,principal.getName());
    }
    /**
     * 사용권, 저작권 판매 등록 필요
     */
}
