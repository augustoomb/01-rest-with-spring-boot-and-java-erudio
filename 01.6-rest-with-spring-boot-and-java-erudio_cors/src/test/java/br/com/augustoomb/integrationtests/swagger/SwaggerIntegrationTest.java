package br.com.augustoomb.integrationtests.swagger;

import br.com.augustoomb.config.TestConfigs;
import br.com.augustoomb.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@Test
	void shouldDisplaySwaggerUIPage() {
		var content = given() // DADA ..
			.basePath("/swagger-ui/index.html") // ... A URL
				.port(TestConfigs.SERVER_PORT) // ... NA PORTA
			.when() // ... QUANDO
				.get() // ... EU FAÇO UM GET
			.then() // ... ENTÃO EU ESPERO
				.statusCode(200) // .. UM STATUS CODE 200
			.extract() // E EXTRAIO
				.body() // O CONTEÚDO DO BODY
					.asString(); // COMO UMA STRING


		assertTrue(content.contains("Swagger UI"));
	}

}
