package br.com.augustoomb.services;

import br.com.augustoomb.controllers.BookController;
import br.com.augustoomb.data.dto.BookDTO;
import br.com.augustoomb.exception.RequiredObjectIsNullException;
import br.com.augustoomb.exception.ResourceNotFoundException;
import br.com.augustoomb.model.Book;
import br.com.augustoomb.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static br.com.augustoomb.mapper.ObjectMapper.parseListObjects;
import static br.com.augustoomb.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@Service
public class BookServices {

    private Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    public List<BookDTO> findAll() {
        logger.info("Finding all Books!");

        var books = repository.findAll();

        var booksDto = parseListObjects(books, BookDTO.class);

        booksDto.forEach(b -> addHateoasLinks(b));

        return booksDto;
    }

    public BookDTO findById(Long id) {
        logger.info("Finding on book!");

        var book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        var bookDto = parseObject(book, BookDTO.class);

        addHateoasLinks(bookDto);

        return bookDto;
    }

    public BookDTO create(BookDTO book) {
        if(book == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one book!");

        var bookEntity = parseObject(book, Book.class);

        var bookSaved = repository.save(bookEntity);

        var bookDto = parseObject(bookSaved, BookDTO.class);

        addHateoasLinks(bookDto);

        return bookDto;
    }

    public BookDTO update(BookDTO book) {
        if(book == null) throw new RequiredObjectIsNullException();

        logger.info("updating one book!");

        var bookFound = repository.findById(book.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        bookFound.setAuthor(book.getAuthor());
        bookFound.setLaunchDate(book.getLaunchDate());
        bookFound.setPrice(book.getPrice());
        bookFound.setTitle(book.getTitle());

        var bookSaved = repository.save(bookFound);

        var bookDto = parseObject(bookSaved, BookDTO.class);

        addHateoasLinks(bookDto);

        return bookDto;
    }

    public void delete(Long id) {
        logger.info("Deleting on book");

        var bookFound = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        repository.delete(bookFound);
    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
