package com.school_management.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration

public class SwaggerConfig {

    @Bean(name = "customSwaggerConfig")
    public OpenAPI swaggerConfig() {
        return new OpenAPI()
                .info(
                new Info().title("School Management App APIs")
                        .description("By Abhay")
                )
                .servers(List.of((new Server().url("http://localhost:8082").description("local")),
                        (new Server().url("https://schoolwise.onrender.com").description("dev"))))
                .tags(Arrays.asList(
                        new Tag().name("Public APIs"),
                        new Tag().name("Users APIs"),
                        new Tag().name("Admin APIs")
                ))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes(
                        "bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                ));
    }
}
