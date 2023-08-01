package com.openpromt.coffeee.swf2023.openpromtserver.copyright.controller;


import com.openpromt.coffeee.swf2023.openpromtserver.copyright.dto.RegisterCopyrightRequest;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.dto.RegisterCopyrightResponse;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity.Copyright;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.service.CopyrightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

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
    public RegisterCopyrightResponse registerCopyright(Principal principal, @RequestBody RegisterCopyrightRequest request) throws NoSuchAlgorithmException{
        copyrightService.registCopyright(request,principal.getName());
        return null;
    }
}
