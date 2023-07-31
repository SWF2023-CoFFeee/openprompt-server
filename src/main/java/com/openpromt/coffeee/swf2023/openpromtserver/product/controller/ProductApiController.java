package com.openpromt.coffeee.swf2023.openpromtserver.product.controller;

import com.openpromt.coffeee.swf2023.openpromtserver.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/api/v2/product")
@RequiredArgsConstructor
@RestController
public class ProductApiController {

    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<?> getAll(@RequestParam("product_type") String product_type){

        JSONObject json = new JSONObject();

        return ResponseEntity.ok(json);
    }
}
