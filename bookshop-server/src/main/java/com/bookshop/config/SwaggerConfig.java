package com.bookshop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
//                        url = "https://www.facebook.com/tutungduong/",
                        email = "tu.tungduong@outlook.com"

                ),
                description = "OpenAPI Documentation for Book Store",
                title = "Book Store API",
                version = "V1"
        ),
        servers = {
                @Server(
                        description = "Local Server",
                        url = "http://localhost:${server.port}"
                )
        }
)
@Configuration
public class SwaggerConfig {

}