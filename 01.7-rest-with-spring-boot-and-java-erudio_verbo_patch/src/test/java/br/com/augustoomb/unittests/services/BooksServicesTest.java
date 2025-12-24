// TESTES UNITÁRIOS

package br.com.augustoomb.unittests.services;

import br.com.augustoomb.data.dto.BookDTO;
import br.com.augustoomb.model.Book;
import br.com.augustoomb.repository.BookRepository;
import br.com.augustoomb.services.BookServices;
import br.com.augustoomb.unittests.mapper.mocks.MockBooks;

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
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BooksServicesTest {
    MockBooks input;

    @InjectMocks
    private BookServices service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBooks();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        Book book = input.mockEntity(1);
        Book persisted = book;
        persisted.setId(1L);

        BookDTO dto = input.mockDTO(1);

        when(repository.save(book)).thenReturn(persisted);

        BookDTO bookDto = input.mockDTO(1);
        var resultBookDto = service.create(bookDto);

        assertNotNull(resultBookDto);

    }

    @Test
    void findById() {

        // SETANDO O QUE QUERO QUE SEJA RETORNADO(MOCKANDO)
        Book book = input.mockEntity(1);
        when(repository.findById(1L)).thenReturn(Optional.of(book));

        var result = service.findById(1L);

        assertNotNull(result);
        assertEquals("Author1", result.getAuthor());

        // TESTAR HATEOAS
        assertTrue(result.getLinks().stream() // Stream do Java 8, permitindo o uso de operações de filtro e busca
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("GET")));
    }

}
