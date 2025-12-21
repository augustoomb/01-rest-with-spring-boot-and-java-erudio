package br.com.augustoomb.integrationtests.controllers.withjson;

import br.com.augustoomb.config.TestConfigs;
import br.com.augustoomb.integrationtests.dto.PersonDTO;
import br.com.augustoomb.integrationtests.testcontainers.AbstractIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.RequestExpectation;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // POR PADRÃO, OS TESTER RODAM EM ORDEM ALEATÓRIA. SETANDO AQUI, FALO QUE VOU COLOCAR UMA ORDEM
class PersonControllerTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static PersonDTO person;


    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); // IGNORA ATRIBUTOS DESCONHECIDOS RETORNADOS. PRECISO DISSO POIS ESTOU RECEBENDO INFORMAÇÕES DE HATEOAS, QUE DARIAM ERRO AQUI NO TESTE

        person = new PersonDTO();
    }

    @Test
    @Order(1)
    void create() throws JsonProcessingException {
        mockPerson();

        specification = new RequestSpecBuilder()
            .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_ERUDIO)
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();


        var content = given(specification) // DADA ..
            .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(person)
            .when() // ... QUANDO
                .post() //
            .then() // ... ENTÃO EU ESPERO
                .statusCode(200) // .. UM STATUS CODE 200
            .extract() // E EXTRAIO
                .body() // O CONTEÚDO DO BODY
                    .asString(); // COMO UMA STRING

        PersonDTO createdPerson = objectMapper.readValue(content, PersonDTO.class); // usado para transformar a resposta da API (que chega como texto/JSON) de volta em um objeto Java (PersonDTO) para que você possa fazer as validações (assertions).
        person = createdPerson;


        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());

        assertTrue(createdPerson.getId() > 0);

        assertEquals("Richard", createdPerson.getFirstName());
        assertEquals("Stallman", createdPerson.getLastName());
        assertEquals("New York City", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
    }

    @Test
    @Order(2)
    void createWithWrongOrigin() throws JsonProcessingException {

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SEMERU)
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();


        var content = given(specification) // DADA ..
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(person)
                .when() // ... QUANDO
                .post() //
                .then() // ... ENTÃO EU ESPERO
                .statusCode(403) // .. UM STATUS CODE 403
                .extract() // E EXTRAIO
                .body() // O CONTEÚDO DO BODY
                .asString(); // COMO UMA STRING


        assertEquals("Invalid CORS request", content);
    }

    @Test
    @Order(3)
    void findById() throws JsonProcessingException {
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();


        var content = given(specification) // DADA ..
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", person.getId())
                .when() // ... QUANDO
                .get("{id}") //
                .then() // ... ENTÃO EU ESPERO
                .statusCode(200) // .. UM STATUS CODE 200
                .extract() // E EXTRAIO
                .body() // O CONTEÚDO DO BODY
                .asString(); // COMO UMA STRING

        PersonDTO createdPerson = objectMapper.readValue(content, PersonDTO.class); // usado para transformar a resposta da API (que chega como texto/JSON) de volta em um objeto Java (PersonDTO) para que você possa fazer as validações (assertions).
        person = createdPerson;

        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());

        assertTrue(createdPerson.getId() > 0);

        assertEquals("Richard", createdPerson.getFirstName());
        assertEquals("Stallman", createdPerson.getLastName());
        assertEquals("New York City", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
    }

    @Test
    @Order(4)
    void findByIdWithWrongOrigin() throws JsonProcessingException {
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SEMERU)
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();


        var content = given(specification) // DADA ..
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", person.getId())
                .when() // ... QUANDO
                .get("{id}") //
                .then() // ... ENTÃO EU ESPERO
                .statusCode(403) // .. UM STATUS CODE 200
                .extract() // E EXTRAIO
                .body() // O CONTEÚDO DO BODY
                .asString(); // COMO UMA STRING


        assertEquals("Invalid CORS request", content);
    }

    private void mockPerson() {
        person.setFirstName("Richard");
        person.setLastName("Stallman");
        person.setAddress("New York City");
        person.setGender("Male");
    }
}