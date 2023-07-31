package com.openpromt.coffeee.swf2023.openpromtserver.user.dto;

import com.openpromt.coffeee.swf2023.openpromtserver.user.entity.User;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserJoinRequestDto {

    private String username;
    private String password;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }

    public void setEncPassword(String encPassword) {
        this.password = encPassword;
    }

}
