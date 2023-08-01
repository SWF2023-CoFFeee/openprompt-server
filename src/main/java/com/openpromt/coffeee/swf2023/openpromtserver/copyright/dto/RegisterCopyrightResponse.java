package com.openpromt.coffeee.swf2023.openpromtserver.copyright.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCopyrightResponse {
    private String ipfs_uri;
    private String copyright_id;
    private String copyright_title;
    private String prompt;
    private String querystring;
}
