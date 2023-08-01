package com.openpromt.coffeee.swf2023.openpromtserver.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -610114713L;

    public static final QUser user = new QUser("user");

    public final com.openpromt.coffeee.swf2023.openpromtserver.util.auditing.QBaseUserEntity _super = new com.openpromt.coffeee.swf2023.openpromtserver.util.auditing.QBaseUserEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> CRE_DTTM = _super.CRE_DTTM;

    public final StringPath password = createString("password");

    public final EnumPath<com.openpromt.coffeee.swf2023.openpromtserver.user.util.Role> role = createEnum("role", com.openpromt.coffeee.swf2023.openpromtserver.user.util.Role.class);

    public final ListPath<com.openpromt.coffeee.swf2023.openpromtserver.ownticket.entity.OwnTicket, com.openpromt.coffeee.swf2023.openpromtserver.ownticket.entity.QOwnTicket> tickets = this.<com.openpromt.coffeee.swf2023.openpromtserver.ownticket.entity.OwnTicket, com.openpromt.coffeee.swf2023.openpromtserver.ownticket.entity.QOwnTicket>createList("tickets", com.openpromt.coffeee.swf2023.openpromtserver.ownticket.entity.OwnTicket.class, com.openpromt.coffeee.swf2023.openpromtserver.ownticket.entity.QOwnTicket.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> UPD_DTTM = _super.UPD_DTTM;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

