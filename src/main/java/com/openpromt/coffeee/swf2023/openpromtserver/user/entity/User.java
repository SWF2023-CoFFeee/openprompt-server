package com.openpromt.coffeee.swf2023.openpromtserver.user.entity;

import com.openpromt.coffeee.swf2023.openpromtserver.user.util.Role;
import com.openpromt.coffeee.swf2023.openpromtserver.util.auditing.BaseUserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String username;
    private String password;
    private String product_list;

    @Enumerated(EnumType.STRING)
    private Role role;
}
