package com.compass.ms_ticket_manager;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Documentation - Ticket Manager")
                        .version("1.0")
                        .description("Documentation of the API endpoints for the Ticket Manager"));
    }
}
