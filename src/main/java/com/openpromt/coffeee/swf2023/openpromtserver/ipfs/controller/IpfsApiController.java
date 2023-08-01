package com.openpromt.coffeee.swf2023.openpromtserver.ipfs.controller;

import com.openpromt.coffeee.swf2023.openpromtserver.ipfs.service.IpfsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequestMapping("/api/v2/ipfs")
@RequiredArgsConstructor
@RestController
public class IpfsApiController {

    private final IpfsService ipfsService;

    @PostMapping(value = "/upload")
    public String saveFile(@RequestParam("file")MultipartFile file){
        return ipfsService.saveFile(file);
    }

    @GetMapping(value = "/file/{hash}")
    public ResponseEntity<?> loadFile(@PathVariable("hash") String hash){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", MediaType.ALL_VALUE);
        byte[] bytes = ipfsService.loadFile(hash);
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(bytes);
    }
}
