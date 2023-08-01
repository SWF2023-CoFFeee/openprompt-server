package com.openpromt.coffeee.swf2023.openpromtserver.user.service;

import com.openpromt.coffeee.swf2023.openpromtserver.auth.JwtProvider;
import com.openpromt.coffeee.swf2023.openpromtserver.user.dto.JoinRequestDto;
import com.openpromt.coffeee.swf2023.openpromtserver.user.dto.LoginRequestDto;
import com.openpromt.coffeee.swf2023.openpromtserver.user.dto.LoginResponseDto;
import com.openpromt.coffeee.swf2023.openpromtserver.user.entity.User;
import com.openpromt.coffeee.swf2023.openpromtserver.user.repository.UserRepository;
import com.openpromt.coffeee.swf2023.openpromtserver.user.util.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;

    public Long join(JoinRequestDto requestDto){
        String rawPassword = requestDto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        requestDto.setEncPassword(encPassword);
        requestDto.setRole(Role.USER);
        User user = userRepository.save(requestDto.toEntity());

        return user.getUserId();
    }

    public LoginResponseDto login(LoginRequestDto requestDto, HttpServletResponse httpServletResponse){
        User user = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(() -> new BadCredentialsException("잘못된 계정정보입니다."));
        if(!bCryptPasswordEncoder.matches(requestDto.getPassword(), user.getPassword()))
            throw new BadCredentialsException("잘못된 계정정보입니다.");

//        Cookie cookie = new Cookie("access_token", jwtProvider.createToken(user.getUsername(), user.getRole()));
//        cookie.setHttpOnly(true); // HttpOnly 속성 설정
//        cookie.setPath("/");
//        cookie.setDomain("ec2-3-35-255-234.ap-northeast-2.compute.amazonaws.com"); // 쿠키의 도메인 설정 (루트 도메인으로 설정)
//        httpServletResponse.addCookie(cookie);

        return LoginResponseDto.builder()
                .user_id(user.getUserId())
                .username(user.getUsername())
                .role(user.getRole())
                .token(jwtProvider.createToken(user.getUsername(), user.getRole()))
                .build();
    }

}
