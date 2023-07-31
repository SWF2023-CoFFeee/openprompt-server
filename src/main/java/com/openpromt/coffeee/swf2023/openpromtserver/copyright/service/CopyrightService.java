package com.openpromt.coffeee.swf2023.openpromtserver.copyright.service;


import com.openpromt.coffeee.swf2023.openpromtserver.copyright.dto.RegisterCopyrightRequest;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity.Copyright;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.repository.CopyrightRepository;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.util.RSAUtil;
import com.openpromt.coffeee.swf2023.openpromtserver.user.entity.User;
import com.openpromt.coffeee.swf2023.openpromtserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CopyrightService {
    private final UserRepository userRepository;
    private final CopyrightRepository copyrightRepository;

    public void registerCopyright(RegisterCopyrightRequest request) throws NoSuchAlgorithmException {
        Optional<User> user;
        Copyright newCopyright = Copyright.getCopyrightByRequest(request);

    }
}
