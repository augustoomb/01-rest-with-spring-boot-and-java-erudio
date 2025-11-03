package br.com.augustoomb.controllers;

import br.com.augustoomb.data.dto.PersonDTO;
import br.com.augustoomb.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    // JÁ QUE O PERSONSERVICE ESTÁ ANOTADO COMO @Service(lá no arquivo dele), ELE ME PERMITE USAR AQUI SEM O new...
    // ISSO É O USO DE INJEÇÃO DE DEPENDÊNCIAS...
    @Autowired // ..COM ISSO(Autowired), O SPRING BOOT USA A INJEÇÃO DE DEPENDÊNCIAS E INSTANCIA SOMENTE QUANDO FOR NECESSÁRIO
    private PersonServices service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO findById(@PathVariable("id") Long id) {

        return service.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDTO> findAll() {

        return service.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO create(@RequestBody PersonDTO person) {

        return service.create(person);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO update(@RequestBody PersonDTO person) {

        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // RETORNA CODE 204
    }
}
