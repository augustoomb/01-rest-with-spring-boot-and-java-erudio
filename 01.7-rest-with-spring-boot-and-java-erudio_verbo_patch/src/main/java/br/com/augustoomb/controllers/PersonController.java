package br.com.augustoomb.controllers;

import br.com.augustoomb.controllers.docs.DefaultApiResponses;
import br.com.augustoomb.data.dto.PersonDTO;
import br.com.augustoomb.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*  @CrossOrigin(origins = "http://localhost:8080")
CONFIGURAÇÃO DE CORS. SE NÃO PASSAR "origins", PERMITE ACESSO PARA TODOS
AQUI EU COMENTEI, POIS O CORS FOI CONFIGURADO EM UM LOCAL UNICO PARA TODA A APLICAÇÃO (package config, file WebConfig)
NO POSTMAN, FAÇO O TESTE COLOCANDO O PARÂMETRO "Origin" NO HEADERS. POR EX:
NA ROTA ONDE COLOCO @CrossOrigin(origins = "http://localhost:8080"), SÓ FUNCIONA SE
EU CONFIGURAR O ORIGIN DO POSTMAN COMO "http://localhost:8080". SE EU CONFIGURAR
O ORIGIN COMO OUTRO LOCAL, DEVOLVE UM ERRO
*/
@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "Person", description = "Endpoints para gerenciar Person") // SWAGGER
public class PersonController {

    @Autowired
    private PersonServices service;

    // FIND ALL
    @Operation(summary = "Find All Person", description = "Find All Person")
    @DefaultApiResponses
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
            )
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    // FIND BY ID
    // @CrossOrigin(origins = "http://localhost:8080") // O CORS FOI CONFIGURADO EM UM LOCAL UNICO PARA TODA A APLICAÇÃO (package config, file WebConfig)
    @Operation(summary = "Find Person by id", description = "Find Person by id")
    @DefaultApiResponses
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(schema = @Schema(implementation = PersonDTO.class))
    )
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public PersonDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    // CREATE
    // @CrossOrigin(origins = {"http://localhost:8080", "https://www.erudio.com.br"}) // O CORS FOI CONFIGURADO EM UM LOCAL UNICO PARA TODA A APLICAÇÃO (package config, file WebConfig)
    @Operation(summary = "Add a new Person", description = "Add a new Person")
    @DefaultApiResponses
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(schema = @Schema(implementation = PersonDTO.class))
    )
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public PersonDTO create(@RequestBody PersonDTO Person) {
        return service.create(Person);
    }

    // UPDATE
    @Operation(summary = "Update a Person", description = "Update a Person")
    @DefaultApiResponses
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(schema = @Schema(implementation = PersonDTO.class))
    )
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public PersonDTO update(@RequestBody PersonDTO Person) {
        return service.update(Person);
    }

    // DELETE
    @Operation(summary = "Add a new Person", description = "Add a new Person")
    @DefaultApiResponses
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // DISABLE A PERSON
    @Operation(summary = "Disable a Person", description = "Disable a Person")
    @DefaultApiResponses
    @PatchMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
//    @DeleteMapping(value = "/{id}")
    public PersonDTO disablePerson(@PathVariable("id") Long id) {
        return service.disablePerson(id);
    }
}










//package br.com.augustoomb.controllers;
//
//import br.com.augustoomb.controllers.docs.PersonControllerDocs;
//import br.com.augustoomb.data.dto.PersonDTO;
//import br.com.augustoomb.services.PersonServices;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/person/v1")
//@Tag(name = "People", description = "Endpoints para gerenciar People") // SWAGGER
//public class PersonController implements PersonControllerDocs { // essa interface "PersonControllerDocs" eu criei para que um pouco do conteúdo do controller ficasse em outro lugar. O conteúdo do swagger fica lá juntamente com a declação dos métodos e aqui eu sobreescrevo eles, usando o override. Não é uma solução ótima, mas quebra o galho!
//
//    // JÁ QUE O PERSONSERVICE ESTÁ ANOTADO COMO @Service(lá no arquivo dele), ELE ME PERMITE USAR AQUI SEM O new...
//    // ISSO É O USO DE INJEÇÃO DE DEPENDÊNCIAS...
//    @Autowired // ..COM ISSO(Autowired), O SPRING BOOT USA A INJEÇÃO DE DEPENDÊNCIAS E INSTANCIA SOMENTE QUANDO FOR NECESSÁRIO
//    private PersonServices service;
//
//
//    @GetMapping(
//            value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
//    @Override
//    public PersonDTO findById(@PathVariable("id") Long id) {
//        return service.findById(id);
//    }
//
//
//    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
//    @Override
//    public List<PersonDTO> findAll() {
//
//        return service.findAll();
//    }
//
//
//    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
//    @Override
//    public PersonDTO create(@RequestBody PersonDTO person) {
//
//        return service.create(person);
//    }
//
//
//    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
//    @Override
//    public PersonDTO update(@RequestBody PersonDTO person) {
//
//        return service.update(person);
//    }
//
//
//    @DeleteMapping(value = "/{id}")
//    @Override
//    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
//        service.delete(id);
//        return ResponseEntity.noContent().build(); // RETORNA CODE 204
//    }
//}
