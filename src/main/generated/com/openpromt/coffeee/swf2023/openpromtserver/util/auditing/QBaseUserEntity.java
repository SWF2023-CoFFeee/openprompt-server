package com.openpromt.coffeee.swf2023.openpromtserver.util.auditing;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseUserEntity is a Querydsl query type for BaseUserEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseUserEntity extends EntityPathBase<BaseUserEntity> {

    private static final long serialVersionUID = -1145182706L;

    public static final QBaseUserEntity baseUserEntity = new QBaseUserEntity("baseUserEntity");

    public final DateTimePath<java.time.LocalDateTime> CRE_DTTM = createDateTime("CRE_DTTM", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> UPD_DTTM = createDateTime("UPD_DTTM", java.time.LocalDateTime.class);

    public QBaseUserEntity(String variable) {
        super(BaseUserEntity.class, forVariable(variable));
    }

    public QBaseUserEntity(Path<? extends BaseUserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseUserEntity(PathMetadata metadata) {
        super(BaseUserEntity.class, metadata);
    }

}

