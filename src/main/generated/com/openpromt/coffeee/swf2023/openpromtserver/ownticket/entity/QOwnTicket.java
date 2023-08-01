package com.openpromt.coffeee.swf2023.openpromtserver.ownticket.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOwnTicket is a Querydsl query type for OwnTicket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOwnTicket extends EntityPathBase<OwnTicket> {

    private static final long serialVersionUID = 906304279L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOwnTicket ownTicket = new QOwnTicket("ownTicket");

    public final com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity.QCopyright copyright_id;

    public final NumberPath<Long> own_id = createNumber("own_id", Long.class);

    public final com.openpromt.coffeee.swf2023.openpromtserver.user.entity.QUser user_id;

    public QOwnTicket(String variable) {
        this(OwnTicket.class, forVariable(variable), INITS);
    }

    public QOwnTicket(Path<? extends OwnTicket> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOwnTicket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOwnTicket(PathMetadata metadata, PathInits inits) {
        this(OwnTicket.class, metadata, inits);
    }

    public QOwnTicket(Class<? extends OwnTicket> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.copyright_id = inits.isInitialized("copyright_id") ? new com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity.QCopyright(forProperty("copyright_id"), inits.get("copyright_id")) : null;
        this.user_id = inits.isInitialized("user_id") ? new com.openpromt.coffeee.swf2023.openpromtserver.user.entity.QUser(forProperty("user_id")) : null;
    }

}

