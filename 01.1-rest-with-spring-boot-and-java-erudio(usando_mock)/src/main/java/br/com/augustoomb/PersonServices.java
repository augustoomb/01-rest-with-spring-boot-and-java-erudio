package br.com.augustoomb;

import br.com.augustoomb.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service // FAZ O PersonServices FICAR DISPONÍVEL E PODER SER INJETADO ONDE FOR PRECISO (INJEÇÃO DE DEPENDÊNCIAS)
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll() {
        logger.info("Finding all People!");

        List<Person> persons = new ArrayList<Person>();

        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    public Person findById(String id) {
        logger.info("Finding one Person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet()); //MOCK SIMULAR BANCO
        person.setFirstName("Augusto");
        person.setLastName("Barbosa");
        person.setAddress("Contagem - MG");
        person.setGender("masculino");

        return person;
    }

    public Person create(Person person) {
        logger.info("Creating one person!");

        return person;
    }

    public Person update(Person person) {
        logger.info("Updating one person!");

        return person;
    }

    public void delete(String id) {
        logger.info("deleting one person!");
    }


    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet()); //MOCK SIMULAR BANCO
        person.setFirstName("Firstname " + i);
        person.setLastName("LastName " + i);
        person.setAddress("Address " + i);
        person.setGender("Gender " + i);

        return person;
    }
}
