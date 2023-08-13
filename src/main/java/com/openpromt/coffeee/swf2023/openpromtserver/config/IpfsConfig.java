package com.openpromt.coffeee.swf2023.openpromtserver.config;

import io.ipfs.api.IPFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
public class IpfsConfig {
    @Value("${SERVER_DOMAIN}")
    private String DOMAIN;
    public IPFS ipfs;
    public IpfsConfig(){
        ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
    }
}
