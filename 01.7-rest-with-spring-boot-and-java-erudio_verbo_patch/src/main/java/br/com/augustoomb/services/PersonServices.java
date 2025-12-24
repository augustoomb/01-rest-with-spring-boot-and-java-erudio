package br.com.augustoomb.services;

import br.com.augustoomb.controllers.PersonController;
import br.com.augustoomb.data.dto.PersonDTO;
import br.com.augustoomb.exception.RequiredObjectIsNullException;
import br.com.augustoomb.exception.ResourceNotFoundException;
import static br.com.augustoomb.mapper.ObjectMapper.parseListObjects;
import static br.com.augustoomb.mapper.ObjectMapper.parseObject;

import br.com.augustoomb.model.Person;
import br.com.augustoomb.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;

@Service // FAZ O PersonServices FICAR DISPONÍVEL E PODER SER INJETADO ONDE FOR PRECISO (INJEÇÃO DE DEPENDÊNCIAS)
public class PersonServices {

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {
        logger.info("Finding all People!");

        // RELACIONADO A: import static br.com.augustoomb.mapper.ObjectMapper.parseListObjects;
        var persons = parseListObjects(repository.findAll(), PersonDTO.class);

//        persons.forEach(p -> addHateoasLinks(p));
        persons.forEach(this::addHateoasLinks); // OUTRA FORMA (MAIS ELEGANTE) DE USAR O QUE FOI FEITO NA LINHA ACIMA


        return persons;
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one Person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        var dto = parseObject(entity, PersonDTO.class);

        // HATEOAS
        addHateoasLinks(dto);

        return dto;
    }



    public PersonDTO create(PersonDTO person) {

        if(person == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one person!");

        var entity = parseObject(person, Person.class);

        var dto =  parseObject(repository.save(entity), PersonDTO.class);

        // HATEOAS
        addHateoasLinks(dto);

        return dto;
    }

    public PersonDTO update(PersonDTO person) {

        if(person == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one person!");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = parseObject(repository.save(entity), PersonDTO.class);

        // HATEOAS
        addHateoasLinks(dto);

        return dto;
    }

    public void delete(Long id) {
        logger.info("deleting one person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }

    @Transactional
    public PersonDTO disablePerson(Long id) {
        logger.info("disabling one person!");

        repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.disabledPerson(id);

        var entity = repository.findById(id).get();

        var dto = parseObject(entity, PersonDTO.class);

        // HATEOAS
        addHateoasLinks(dto);

        return dto;
    }

    // HATEOAS
    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).disablePerson(dto.getId())).withRel("disable").withType("PATCH"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
