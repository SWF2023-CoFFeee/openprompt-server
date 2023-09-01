package com.openpromt.coffeee.swf2023.openpromtserver.product.service;

import com.openpromt.coffeee.swf2023.openpromtserver.copyright.dto.RegisterCopyrightRequest;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity.Copyright;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.repository.CopyrightRepository;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.service.CopyrightService;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.util.RSAUtil;
import com.openpromt.coffeee.swf2023.openpromtserver.ipfs.service.FileService;
import com.openpromt.coffeee.swf2023.openpromtserver.ipfs.service.IpfsService;
import com.openpromt.coffeee.swf2023.openpromtserver.ownticket.entity.OwnTicket;
import com.openpromt.coffeee.swf2023.openpromtserver.ownticket.repository.OwnTicketRepository;
import com.openpromt.coffeee.swf2023.openpromtserver.product.dto.GetProductDetailResponse;
import com.openpromt.coffeee.swf2023.openpromtserver.product.dto.GetProductListResponse;
import com.openpromt.coffeee.swf2023.openpromtserver.product.dto.RegistProductRequest;
import com.openpromt.coffeee.swf2023.openpromtserver.product.entity.Product;
import com.openpromt.coffeee.swf2023.openpromtserver.product.repository.ProductRepository;
import com.openpromt.coffeee.swf2023.openpromtserver.user.entity.User;
import com.openpromt.coffeee.swf2023.openpromtserver.user.repository.UserRepository;
import com.openpromt.coffeee.swf2023.openpromtserver.util.googlestorage.GoogleStorageUtil;
import com.openpromt.coffeee.swf2023.openpromtserver.util.httputil.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
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
    private final FileService fileService;
    private final IpfsService ipfsService;
    private final GoogleStorageUtil googleStorageUtil;
    private final HttpUtil httpUtil;

    private final String ipfsBaseUrl = "https://ipfs.io/ipvs/";

    public List<GetProductListResponse> getProductListByProductType(String product_type) {
        return productRepository.findAllByProductType(product_type).stream().map(Product::productToListResponse).collect(Collectors.toList());
    }


    public GetProductDetailResponse getProductDetail(Long product_id) {
        Product product =productRepository.findById(product_id).orElseThrow(NoSuchElementException::new);
        return Product.productToDetailResponse(product);
    }


    public String buyCopyright(Long product_id, String buyer) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, IOException, BadPaddingException, InvalidKeyException, ClassNotFoundException {
        Product product = productRepository.findById(product_id).orElseThrow(NoSuchElementException::new);
        Copyright copyright = product.getCopyrightId();
        String copyright_id = copyright.getCopyrightId();
        User buyUser = userRepository.findByUsername(buyer).orElseThrow(NoSuchElementException::new);
        List<Product> productList = productRepository.findAllByCopyrightId(copyright);

        for(Product p : productList){
            p.cancelSellingProduct();
            productRepository.save(product);
        }

        JSONObject json = new JSONObject(new String(ipfsService.loadFile(copyright.getCopyrightId())));
        String encryptedPrompt = json.get("prompt").toString();
        String ai_type = json.get("ai_type").toString();
        String copyright_title = json.get("copyright_title").toString();

        String base64PrivKey = copyright.getPrivKey();
        PrivateKey privateKey = RSAUtil.getPrivateKeyFromBase64Encrypted(base64PrivKey);
        String plainText = RSAUtil.decryptRSA(encryptedPrompt,privateKey);

        // IPFS(productid로 cid갖고와) 에 요청해서 데이터 받고 datq 변환하고 decrypt 하고 plaintext 다시 암호화해서 IPFS에 재요청.

        KeyPair keyPair = RSAUtil.genRSAKeyPair();
        PublicKey newPubKey = keyPair.getPublic();
        PrivateKey newPrivKey = keyPair.getPrivate();

        String newEncryptedPrompt = RSAUtil.encryptRSA(plainText, newPubKey);

        MultipartFile multipartFile = fileService.convertJsonToMultipartfile(new RegisterCopyrightRequest(newEncryptedPrompt, ai_type,copyright_title), buyer); // 요청받은 request json을 Multipartfile로 변환
        String hash = ipfsService.saveFile(multipartFile); // IPFS 네트워크에 등록 후 hash값 반환

        copyright.transferCopyright(buyUser, hash);

        return copyrightRepository.save(copyright).getCopyrightId();

    }


    public void buyTicket(Long product_id, String username) {
        User buyer = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        Product product = productRepository.findById(product_id).orElseThrow(NoSuchElementException::new);
        ownTicketRepository.save(new OwnTicket(buyer, product.getCopyrightId()));
    }

    public void registerProduct(RegistProductRequest request, MultipartFile file) throws IOException {
        System.out.println("asd"+request.getCopyright_id());
        Copyright copyright = copyrightRepository.findById(request.getCopyright_id()).orElseThrow(NoSuchElementException::new);
        Product newProduct = Product.registProductRequestToProduct(request);
        if(!(file ==null))
            newProduct.updateThumbnail(googleStorageUtil.getGoogleStorageUrl(file));

        newProduct.updateCopyright(copyright);
        productRepository.save(newProduct);
        return;
    }

}
