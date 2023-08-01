package com.openpromt.coffeee.swf2023.openpromtserver.copyright.controller;


import com.openpromt.coffeee.swf2023.openpromtserver.copyright.dto.RegisterCopyrightRequest;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.service.CopyrightService;
import com.openpromt.coffeee.swf2023.openpromtserver.util.jaccard.Jaccard;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

@Slf4j
@RequestMapping("/api/v2/copyright")
@RequiredArgsConstructor
@RestController
@Api("CopyrightApi: registeCopyright")
public class CopyrightApiController {
    private static final Logger logger = LoggerFactory.getLogger(CopyrightApiController.class.getSimpleName());
    private final CopyrightService copyrightService;

    @PostMapping("/")
    @ApiOperation(value="저작권 등록", notes = "RegisterCopyrightRequest를 입력받아 프롬프트 암호화, IPFS metadata 전송 및 URI 받아옴")
    public String registerCopyright(Principal principal, @RequestBody RegisterCopyrightRequest request) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        return copyrightService.registCopyright(request,principal.getName());
    }

    @GetMapping("/")
    public String getDecryptedPrompt(@RequestParam String copyright_id, Principal principal) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, IOException, BadPaddingException, InvalidKeyException, ClassNotFoundException {
        return copyrightService.getDecryptedPrompt(copyright_id,principal.getName());
    }

    @PostMapping("/test")
    public Double testJakard(@RequestParam("string1")String string1, @RequestParam("string2")String string2){
        Double similarity = Jaccard.jaccardSimilarity(string1, string2);
        return similarity;
    }
}
