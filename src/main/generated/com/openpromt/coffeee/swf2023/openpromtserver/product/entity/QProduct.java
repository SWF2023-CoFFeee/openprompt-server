package com.openpromt.coffeee.swf2023.openpromtserver.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -1323705449L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final com.openpromt.coffeee.swf2023.openpromtserver.util.auditing.QBaseEntity _super = new com.openpromt.coffeee.swf2023.openpromtserver.util.auditing.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final EnumPath<com.openpromt.coffeee.swf2023.openpromtserver.product.util.AIType> AItype = createEnum("AItype", com.openpromt.coffeee.swf2023.openpromtserver.product.util.AIType.class);

    public final com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity.QCopyright copyrightId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> CRE_DTTM = _super.CRE_DTTM;

    //inherited
    public final NumberPath<Long> CRE_ID = _super.CRE_ID;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final EnumPath<com.openpromt.coffeee.swf2023.openpromtserver.product.util.ProductType> productType = createEnum("productType", com.openpromt.coffeee.swf2023.openpromtserver.product.util.ProductType.class);

    public final BooleanPath status = createBoolean("status");

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> UPD_DTTM = _super.UPD_DTTM;

    //inherited
    public final NumberPath<Long> UPD_ID = _super.UPD_ID;

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.copyrightId = inits.isInitialized("copyrightId") ? new com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity.QCopyright(forProperty("copyrightId"), inits.get("copyrightId")) : null;
    }

}

