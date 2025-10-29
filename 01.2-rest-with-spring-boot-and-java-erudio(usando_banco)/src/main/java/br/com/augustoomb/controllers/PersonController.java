package br.com.augustoomb.controllers;

import br.com.augustoomb.services.PersonServices;
import br.com.augustoomb.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    // JÁ QUE O PERSONSERVICE ESTÁ ANOTADO COMO @Service, ELE ME PERMITE USAR SEM O new...
    // ISSO É O USO DE INJEÇÃO DE DEPENDÊNCIAS...
    @Autowired // ..COM ISSO(Autowired), O SPRING BOOT USA A INJEÇÃO DE DEPENDÊNCIAS E INSTANCIA SOMENTE QUANDO FOR NECESSÁRIO
    private PersonServices service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable("id") Long id) {

        return service.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() {

        return service.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person) {

        return service.create(person);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person) {

        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // RETORNA CODE 204
    }
}
