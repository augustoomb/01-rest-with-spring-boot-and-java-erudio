package br.com.augustoomb.controllers;

import br.com.augustoomb.controllers.docs.DefaultApiResponses;
import br.com.augustoomb.data.dto.BookDTO;
import br.com.augustoomb.services.BookServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Books", description = "Endpoints para gerenciar Books") // SWAGGER
public class BookController {

    @Autowired
    private BookServices service;

    // FIND ALL
    @Operation(summary = "Find All Books", description = "Find All Books")
    @DefaultApiResponses
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))
            )
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> findAll() {
        return service.findAll();
    }

    // FIND BY ID
    @Operation(summary = "Find book by id", description = "Find book by id")
    @DefaultApiResponses
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(schema = @Schema(implementation = BookDTO.class))
    )
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public BookDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    // CREATE
    @Operation(summary = "Add a new book", description = "Add a new book")
    @DefaultApiResponses
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(schema = @Schema(implementation = BookDTO.class))
    )
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BookDTO create(@RequestBody BookDTO book) {
        return service.create(book);
    }

    // UPDATE
    @Operation(summary = "Update a book", description = "Update a book")
    @DefaultApiResponses
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(schema = @Schema(implementation = BookDTO.class))
    )
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BookDTO update(@RequestBody BookDTO book) {
        return service.update(book);
    }

    // DELETE
    @Operation(summary = "Add a new book", description = "Add a new book")
    @DefaultApiResponses
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

/*
ResponseEntity (a classe): Usado para encapsular a Resposta HTTP completa (Status Code, Cabeçalhos e Corpo).
 */