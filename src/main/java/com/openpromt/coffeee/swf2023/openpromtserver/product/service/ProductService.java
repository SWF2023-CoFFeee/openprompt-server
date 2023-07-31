package com.openpromt.coffeee.swf2023.openpromtserver.product.service;

import com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity.Copyright;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.repository.CopyrightRepository;
import com.openpromt.coffeee.swf2023.openpromtserver.ownticket.entity.OwnTicket;
import com.openpromt.coffeee.swf2023.openpromtserver.ownticket.repository.OwnTicketRepository;
import com.openpromt.coffeee.swf2023.openpromtserver.product.dto.GetProductDetailResponse;
import com.openpromt.coffeee.swf2023.openpromtserver.product.dto.GetProductListResponse;
import com.openpromt.coffeee.swf2023.openpromtserver.product.entity.Product;
import com.openpromt.coffeee.swf2023.openpromtserver.product.repository.ProductRepository;
import com.openpromt.coffeee.swf2023.openpromtserver.user.entity.User;
import com.openpromt.coffeee.swf2023.openpromtserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final CopyrightRepository copyrightRepository;
    private final UserRepository userRepository;
    private final OwnTicketRepository ownTicketRepository;
    public List<GetProductListResponse> getProductListByProductType(String product_type) {
        return productRepository.findAllByProductType(product_type).stream().map(Product::productToListResponse).collect(Collectors.toList());
    }

    public GetProductDetailResponse getProductDetail(Long product_id) {
        Product product =productRepository.findById(product_id).orElseThrow(NoSuchElementException::new);
        return Product.productToDetailResponse(product);
    }


    public Long buyCopyright(Long product_id, String username) {
        Product product = productRepository.findById(product_id).orElseThrow(NoSuchElementException::new);
        Copyright copyright = product.getCopyright_id();

        List<Product> productList = productRepository.findByCopyrightId(copyright.getCopyright_id());
        for(Product p : productList){
            p.cancelSellingProduct();
            productRepository.save(product);
        }

        User buyer = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        copyright.sellCopyright(buyer);
        Long copyright_id =copyrightRepository.save(copyright).getCopyright_id();

        /**
         * IPFS 갱신 코드 넣어주세요.
         */

        return copyright_id;
    }


    public void buyTicket(Long product_id, String username) {
        User buyer = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        Product product = productRepository.findById(product_id).orElseThrow(NoSuchElementException::new);
        ownTicketRepository.save(new OwnTicket(buyer, product.getCopyright_id()));
    }
}
