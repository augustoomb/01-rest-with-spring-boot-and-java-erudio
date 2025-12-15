package br.com.augustoomb.integrationtests.testcontainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

// CONFIGURAÇÕES PARA USAR O TESTCONTAINERS (TESTES DE INTEGRAÇÃO)

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

    // ESTÁTICO AQUI, POIS SÓ PODE TER UM RODANDO POR VEZ
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:9.1.0");

        // ESTATICO AQUI POIS SÓ PODE TER UM RODANDO POR VEZ - 1 INSTANCIA
        private static void startContainers() {
            // JÁ QUE NA LINHA ONDE EU CRIEI O mysql ACIMA EU NÃO SETEI VÁRIAVEIS (COMO
            // SENHA, ETC), AQUI ESTOU DIZENDO PARA USAR VALORES DEFAULT
            Startables.deepStart(Stream.of(mysql)).join(); // BASICAMENTE INICIA UM CONTAINER COM MEU SQL PARA TESTES
        }

        private static Map<String, String> createConnectionConfiguration() {
            // PROPRIEDADES (url, username e password) SÃO AS QUE EU USO NO application.yml
            // PRINCIPAL DO PROJETO
            // IMPORTANTE: O TEST CONTAINERS CRIARÁ O BANCO (name, url, user, pass e etc)
            // DINAMICAMENTE!!
            return Map.of(
                    "spring.datasource.url", mysql.getJdbcUrl(),
                    "spring.datasource.username", mysql.getUsername(),
                    "spring.datasource.password", mysql.getPassword());
        }

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers(); // iniciando aqui!

            // PEGANDO AS VARIÁVEIS DE AMBIENTE QUE FORAM DEFINIDAS NO application.yml ...
            ConfigurableEnvironment environment = applicationContext.getEnvironment();

            // CRIANDO AS CONFIGURAÇÕES DE CONEXÃO E CHAMANDO O METODO createConnectionConfiguration
            MapPropertySource testcontainers = new MapPropertySource("testcontainers",
                    (Map) createConnectionConfiguration()); // CASTING PARA MAP

            // ... E ADICIONANDO AS VARIÁVEIS DE AMBIENTE AO AMBIENTE DO SPRING
            environment.getPropertySources().addFirst(testcontainers);
        }

    }

}
