package br.com.augustoomb.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// SWAGGER
@Configuration
public class OpenApiConfig {

    @Bean // UM BEAN É UM OBJETO QUE É MONTADO E INSTANCIADO PELO SPRING (inje. dep.)
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("REST APIs do Zero com Spring")
                        .version("v1")
                        .description("Criação da API do zero com Java, Spring, Kubernetes e Docker")
                        .termsOfService("https://github.com/augustoomb")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/augustoomb")
                        )
                );
    }
}
