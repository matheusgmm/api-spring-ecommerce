package com.muccio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
            .info(
                new Info()
                    .title("API ECommerce")
                    .version("v1")
                    .description("API for managing an e-commerce platform, offering endpoints for operations with categories, products, orders, users, and other essential functionalities for running an online store. This API allows integration with frontend systems and mobile applications, providing a robust and secure interface for the administration and operation of a virtual store.")
                    .termsOfService("http://terms-of-service.com.br")
                    .license(
                        new License().name("Apache 2.0")
                            .url("http://license-url.com.br")
                    )
            );

    }
}
