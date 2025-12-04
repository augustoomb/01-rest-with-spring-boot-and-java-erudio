package br.com.augustoomb.unitetests.mapper.mocks;

import br.com.augustoomb.data.dto.BookDTO;
import br.com.augustoomb.model.Book;

import java.time.Instant;
import java.util.Date;

public class MockBooks {

    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setAuthor("Author"+number);

        // 1. Define a string ISO 8601 (Date + Time + Offset)
        String dateString = "2025-12-03T17:45:35.294+00:00";

        // 2. Converte a string diretamente para um Instant (ponto no tempo universal)
        Instant instant = Instant.parse(dateString);

        // 3. Converte o Instant para o tipo legado java.util.Date
        book.setLaunchDate(Date.from(instant));

        book.setPrice(100.00);
        book.setTitle("Title"+number);

        return book;
    }

    public BookDTO mockDTO(Integer number) {
        BookDTO bookDto = new BookDTO();
        bookDto.setId(number.longValue());
        bookDto.setAuthor("Author"+number);

        // 1. Define a string ISO 8601 (Date + Time + Offset)
        String dateString = "2025-12-03T17:45:35.294+00:00";

        // 2. Converte a string diretamente para um Instant (ponto no tempo universal)
        Instant instant = Instant.parse(dateString);

        // 3. Converte o Instant para o tipo legado java.util.Date
        bookDto.setLaunchDate(Date.from(instant));

        bookDto.setPrice(100.00);
        bookDto.setTitle("Title"+number);

        return bookDto;
    }
}
