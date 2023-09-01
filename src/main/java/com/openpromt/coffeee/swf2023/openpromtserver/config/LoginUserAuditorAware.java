package com.openpromt.coffeee.swf2023.openpromtserver.config;

import com.openpromt.coffeee.swf2023.openpromtserver.auth.PrincipalDetailsService;
import com.openpromt.coffeee.swf2023.openpromtserver.user.entity.User;
import com.openpromt.coffeee.swf2023.openpromtserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class LoginUserAuditorAware implements AuditorAware<Long> {

    private final UserRepository userRepository;
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> user = userRepository.findByUsername(authentication.getName());
        if(user.isEmpty())
            return null;

        return Optional.ofNullable(user.get().getUserId());
    }
}