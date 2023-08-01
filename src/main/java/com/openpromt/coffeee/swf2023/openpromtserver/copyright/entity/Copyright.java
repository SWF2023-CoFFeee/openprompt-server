package com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity;

import com.openpromt.coffeee.swf2023.openpromtserver.copyright.dto.RegisterCopyrightRequest;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.util.RSAUtil;
import com.openpromt.coffeee.swf2023.openpromtserver.ownticket.entity.OwnTicket;
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
    private String copyrightId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "own_id")
    private OwnTicket ownTicket;

    private String copyrightTitle;
    @Column(columnDefinition = "LONGTEXT")
    private String privKey;
    @Column(columnDefinition = "LONGTEXT")
    private String pubKey;

    public Copyright(String copyright_title, String priv_key, String pub_key){
        this.privKey=priv_key;
        this.pubKey=pub_key;
        this.copyrightTitle = copyright_title;
    }


    public void transferCopyright(User buyer, String hash){
        this.user = buyer;
        this.copyrightId=hash;
    }

    public void updateCopyrightId(String hash) {
        this.copyrightId=hash;
    }
}
