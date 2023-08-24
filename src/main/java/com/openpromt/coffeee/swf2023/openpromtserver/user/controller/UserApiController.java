package com.openpromt.coffeee.swf2023.openpromtserver.user.controller;

import com.openpromt.coffeee.swf2023.openpromtserver.user.dto.JoinRequestDto;
import com.openpromt.coffeee.swf2023.openpromtserver.user.dto.LoginRequestDto;
import com.openpromt.coffeee.swf2023.openpromtserver.user.dto.LoginResponseDto;
import com.openpromt.coffeee.swf2023.openpromtserver.user.service.UserService;
import com.openpromt.coffeee.swf2023.openpromtserver.util.jaccard.Jaccard;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;


@Slf4j
@RequiredArgsConstructor
@Api("UserApi : Register, Login")
@RequestMapping("/api/v2/user")
@RestController
public class UserApiController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserApiController.class.getSimpleName());
    private static final String SET_COOKIE = "Set-Cookie";

    @Value("${SERVER_DOMAIN}")
    private String DOMAIN;

    @ApiOperation(value = "회원가입", notes = "JoinRequestDto를 입력받아 회원가입")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody JoinRequestDto joinRequestDto) throws JSONException {
        System.out.println("Asdasdasdsad");
        Long userid = userService.join(joinRequestDto);
//        JSONObject json = new JSONObject();
//        json.put("code", 200);
//        json.put("message", "회원가입 성공");
//        json.put("user id", userid);
        return ResponseEntity.ok(userid);
    }

    @ApiOperation(value = "로그인", notes = "LoginRequestDto를 입력받아 로그인")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse httpServletResponse) throws NoSuchFieldException {
        LoginResponseDto loginResponseDto = userService.login(requestDto, httpServletResponse);
        ResponseCookie cookie = ResponseCookie.from("Token", loginResponseDto.getToken())
                .httpOnly(true)
                .sameSite("none")
                .secure(true)
                .path("/api/v2")
                .domain("localhost")
                .build();
        return ResponseEntity.ok().header(SET_COOKIE, cookie.toString()).body(loginResponseDto);
    }

    @GetMapping("/test")
    public String test(@CookieValue("Token") String token, HttpServletRequest request){
        System.out.println(token);
        return null;
    }

    @ApiOperation(value = "로그아웃", notes = "현재 로그인된 사용자의 세션을 종료하고 쿠키를 삭제하여 로그아웃")
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse httpServletResponse) {
        // 쿠키 삭제
        ResponseCookie cookie = ResponseCookie.from("Token", "")
                .httpOnly(true)
                .sameSite("none")
                .secure(true)
                .path("/")
                .domain("localhost")
                .maxAge(0)
                .build();

        httpServletResponse.addHeader(SET_COOKIE, cookie.toString());

        return ResponseEntity.ok().build();
    }
//
//    @PostMapping("/test")
//    public Double testJakard(@RequestParam("string1")String string1, @RequestParam("string2")String string2){
//        double similarity = Jaccard.jaccardSimilarity(string1, string2);
//        return similarity;
//    }
}
