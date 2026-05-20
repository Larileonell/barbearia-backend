package com.barbearia.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Barbearia API")
                        .description("API para gerenciamento de agendamentos de barbearia")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("AllanaOliveira")
                                .email("larissaleonel98@email.com")));
    }
}