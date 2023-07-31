package com.openpromt.coffeee.swf2023.openpromtserver.ownticket.entity;

import com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity.Copyright;
import com.openpromt.coffeee.swf2023.openpromtserver.product.entity.Product;
import com.openpromt.coffeee.swf2023.openpromtserver.user.entity.User;
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
public class OwnTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long own_id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="copyright_id")
    private Copyright copyright_id;

    public OwnTicket(User buyer, Copyright copyright_id) {
        this.user_id = buyer;
        this.copyright_id = copyright_id;
    }
}
