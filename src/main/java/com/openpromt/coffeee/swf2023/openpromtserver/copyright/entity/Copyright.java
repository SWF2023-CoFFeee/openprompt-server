package com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity;

import com.openpromt.coffeee.swf2023.openpromtserver.copyright.dto.RegisterCopyrightRequest;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.util.RSAUtil;
import com.openpromt.coffeee.swf2023.openpromtserver.user.entity.User;
import com.openpromt.coffeee.swf2023.openpromtserver.util.auditing.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Copyright extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long copyrightId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private String copyright_title;
    private String privKey;
    private String pubKey;

    public Copyright(String copyright_title, String priv_key, String pub_key){
        this.privKey=priv_key;
        this.pubKey=pub_key;
        this.copyright_title = copyright_title;
    }

    public static Copyright getCopyrightByRequest(RegisterCopyrightRequest request,User user) throws NoSuchAlgorithmException {
        KeyPair keyPair = RSAUtil.genRSAKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        return new Copyright(request.getCopyright_title(),RSAUtil.getBase64PublicKey(publicKey), RSAUtil.getBase64PrivateKey(privateKey));
    }

    public void sellCopyright(User buyer) {
        this.user = buyer;
    }
}
