package com.openpromt.coffeee.swf2023.openpromtserver.user.service;

import com.openpromt.coffeee.swf2023.openpromtserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

}
