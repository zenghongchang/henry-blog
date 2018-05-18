package com.tofba;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Value("${swagger.show}")
    private boolean swaggerShow;
    
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).enable(swaggerShow).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("cn.luischen.controller")).paths(PathSelectors.any()).build();
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Henry Site Swagger Restful API").description("曾洪昌的个人网站").termsOfServiceUrl("").contact("曾洪昌").version("1.0").build();
    }
}