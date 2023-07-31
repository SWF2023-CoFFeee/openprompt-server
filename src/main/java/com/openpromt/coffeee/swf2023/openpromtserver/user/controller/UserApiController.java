package com.openpromt.coffeee.swf2023.openpromtserver.user.controller;

import com.openpromt.coffeee.swf2023.openpromtserver.auth.PrincipalDetails;
import com.openpromt.coffeee.swf2023.openpromtserver.user.dto.JoinRequestDto;
import com.openpromt.coffeee.swf2023.openpromtserver.user.dto.LoginRequestDto;
import com.openpromt.coffeee.swf2023.openpromtserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;

@Slf4j
@RequestMapping("/api/v2/user")
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody JoinRequestDto joinRequestDto) throws JSONException {

        Long userid = userService.join(joinRequestDto);
//        JSONObject json = new JSONObject();
//        json.put("code", 200);
//        json.put("message", "회원가입 성공");
//        json.put("user id", userid);
        return ResponseEntity.ok(userid);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse httpServletResponse){
        return new ResponseEntity<>(userService.login(requestDto, httpServletResponse), HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(HttpServletRequest request, Principal principal){
        System.out.println(principal.getName());
        return request.getCookies()[0].getName();
    }
}
