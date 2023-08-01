package com.openpromt.coffeee.swf2023.openpromtserver.copyright.controller;


import com.openpromt.coffeee.swf2023.openpromtserver.copyright.dto.RegisterCopyrightRequest;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.dto.RegisterCopyrightResponse;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity.Copyright;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.service.CopyrightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;

@Slf4j
@RequestMapping("/api/v2/copyright")
@RequiredArgsConstructor
@RestController
public class CopyrightApiController {
    private static final Logger logger = LoggerFactory.getLogger(CopyrightApiController.class.getSimpleName());
    private final CopyrightService copyrightService;
    public RegisterCopyrightResponse registerCopyright(Principal principal, @RequestBody RegisterCopyrightRequest request) throws NoSuchAlgorithmException {
        copyrightService.registCopyright(request,principal.getName());
        return null;
    }
}
