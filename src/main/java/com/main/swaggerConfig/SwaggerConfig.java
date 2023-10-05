package com.main.swaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("your.package.controller")) // Update with your package name
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(createSecuritySchemes());
    }

    private List<SecurityScheme> createSecuritySchemes() {
        ApiKey apiKey = new ApiKey("JWT", HttpHeaders.AUTHORIZATION, "header");
        return Collections.singletonList(apiKey);
    }
}
