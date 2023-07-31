//package com.openpromt.coffeee.swf2023.openpromtserver.config;
//
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.RequestHandler;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.function.Predicate;
//
//@Configuration
//public class SwaggerConfig {
//
//    private static final String API_NAME = "Open Prompt API";
//    private static final String API_VERSION = "0.0.1";
//    private static final String API_DESCRIPTION = "Open Prompt API 명세서";
//
//    private Docket docket(String groupName, Predicate<String> selector){
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(this.apiInfo())
//                .useDefaultResponseMessages(false)
//                .groupName("testApi")
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/api/**"))
//                .build();
//    }
//
//    private ApiInfo apiInfo(){
//        return new ApiInfoBuilder()
//                .title(API_NAME)
//                .version(API_VERSION)
//                .description(API_DESCRIPTION)
//                .build();
//    }
//}
