package com.openpromt.coffeee.swf2023.openpromtserver.util.auditing;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEntity is a Querydsl query type for BaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseEntity extends EntityPathBase<BaseEntity> {

    private static final long serialVersionUID = 564695651L;

    public static final QBaseEntity baseEntity = new QBaseEntity("baseEntity");

    public final DateTimePath<java.time.LocalDateTime> CRE_DTTM = createDateTime("CRE_DTTM", java.time.LocalDateTime.class);

    public final NumberPath<Long> CRE_ID = createNumber("CRE_ID", Long.class);

    public final DateTimePath<java.time.LocalDateTime> UPD_DTTM = createDateTime("UPD_DTTM", java.time.LocalDateTime.class);

    public final NumberPath<Long> UPD_ID = createNumber("UPD_ID", Long.class);

    public QBaseEntity(String variable) {
        super(BaseEntity.class, forVariable(variable));
    }

    public QBaseEntity(Path<? extends BaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseEntity(PathMetadata metadata) {
        super(BaseEntity.class, metadata);
    }

}

