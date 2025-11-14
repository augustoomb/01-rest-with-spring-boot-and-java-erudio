package br.com.augustoomb.services;

import br.com.augustoomb.model.Person;
import br.com.augustoomb.repository.PersonRepository;
import br.com.augustoomb.unitetests.mapper.mocks.MockPerson;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // SIGNIFICA QUE OS TESTES VÃO "DURAR" APENAS PARA ESSA CLASSE, NAÕ INTERFERINDO EM OUTRAS
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1L);


        /* when:
        "Sempre que, dentro do PersonServicesTest, alguém chamar o metodo findById do meu
        repositório mockado (repository) e passar o ID 1L, não chame o banco de dados de
        verdade. Em vez disso, retorne imediatamente o objeto Person que eu criei para o teste,
        embrulhado em um Optional.
        Optional<T> é uma classe do Java 8 que funciona como um container para um objeto que pode ou não estar presente."
         */
        when(repository.findById(1L)).thenReturn(Optional.of(person));


        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/person/v1/1")
                && link.getType().equals("GET"))

        );

        // 17:45
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}