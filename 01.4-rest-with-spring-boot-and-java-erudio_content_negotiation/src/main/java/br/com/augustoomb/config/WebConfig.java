package br.com.augustoomb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// VOU USAR NO CONTROLLER - SETAR NO "MediaType" das rotas
// USADA A LIB fasterxml, que é usado para retornar formatos diferentes do JSON padrão

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        // DEIXAR O CLIENTE ESCOLHER QUE TIPO DE RETORNO ELE VAI RECEBER (XML, JSON, ETC)



        // VIA QUERY PARAMS EX: http://localhost:8080/api/person/v1/2?mediaType=xml
//        configurer.favorParameter(true)
//                .parameterName("mediaType") // NOME DO PARÂMETRO QUE SERÁ USADO NA URL
//                .ignoreAcceptHeader(true) // QUANDO O USUÁRIO TENTA MANDAR UM HEADER SETANDO UM FORMATO, VAI SER IGNORADO,
//                .useRegisteredExtensionsOnly(false) // VAMOS SETAR AS EXTENSÕES A SEREM USADAS
//                .defaultContentType(MediaType.APPLICATION_JSON) // TIPO DEFAULT
//                .mediaType("json", MediaType.APPLICATION_JSON) //TIPOS QUE SERÃO SUPORTADOS
//                .mediaType("xml", MediaType.APPLICATION_XML); //TIPOS QUE SERÃO SUPORTADOS


        // VIA HEADER (RECOMENDADO) EX: http://localhost:8080/api/person/v1/2
        // NO HEADER, SETAR O SEGUINTE:
            // Key: Accept
            // Value: application/xml (ou json, que é o padrão)
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false) // VAMOS SETAR AS EXTENSÕES A SEREM USADAS
                .defaultContentType(MediaType.APPLICATION_JSON) // TIPO DEFAULT
                .mediaType("json", MediaType.APPLICATION_JSON) //TIPOS QUE SERÃO SUPORTADOS
                .mediaType("xml", MediaType.APPLICATION_XML) //TIPOS QUE SERÃO SUPORTADOS
                .mediaType("yaml", MediaType.APPLICATION_YAML); //TIPOS QUE SERÃO SUPORTADOS
    }
}
