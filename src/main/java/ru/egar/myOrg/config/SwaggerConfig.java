package ru.egar.myOrg.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "MyOrg Api",
                description = "MyOrg", version = "1.0.0",
                contact = @Contact(
                        name = "Artem",
                        email = "alekhinart@yandex.ru"
                ))
)
public class SwaggerConfig {
}
