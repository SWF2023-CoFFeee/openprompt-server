package com.openpromt.coffeee.swf2023.openpromtserver.copyright.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCopyrightRequest {
    private String prompt;
    private String AI_type;
    private String copyright_title;
}
