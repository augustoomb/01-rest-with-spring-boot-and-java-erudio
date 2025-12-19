//package br.com.augustoomb.controllers.docs;
//
//import br.com.augustoomb.data.dto.PersonDTO;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.ArraySchema;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//public interface PersonControllerDocs {
//    @Operation( // SWAGGER
//            summary = "Find a Person",
//            description = "Find a specific person by ID",
//            tags = {"People"},
//            responses = {
//                    @ApiResponse(
//                            description = "Success",
//                            responseCode = "200",
//                            content = @Content(schema = @Schema(implementation = PersonDTO.class))
//                    ),
//                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
//                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
//                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
//                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
//                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
//            }
//    )
//    PersonDTO findById(@PathVariable("id") Long id);
//
//    @Operation( // SWAGGER
//            summary = "Find All People",
//            description = "Find All People",
//            tags = {"People"},
//            responses = {
//                    @ApiResponse(
//                            description = "Success",
//                            responseCode = "200",
//                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)))}
//                    ),
//                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
//                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
//                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
//                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
//                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
//            }
//    )
//    List<PersonDTO> findAll();
//
//    @Operation( // SWAGGER
//            summary = "Adds a new Person",
//            description = "Adds a new Person",
//            tags = {"People"},
//            responses = {
//                    @ApiResponse(
//                            description = "Success",
//                            responseCode = "200",
//                            content = @Content(schema = @Schema(implementation = PersonDTO.class))
//                    ),
//                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
//                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
//                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
//                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
//                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
//            }
//    )
//    PersonDTO create(@RequestBody PersonDTO person);
//
//    @Operation( // SWAGGER
//            summary = "Update Person by ID",
//            description = "Update Person by ID",
//            tags = {"People"},
//            responses = {
//                    @ApiResponse(
//                            description = "Success",
//                            responseCode = "200",
//                            content = @Content(schema = @Schema(implementation = PersonDTO.class))
//                    ),
//                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
//                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
//                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
//                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
//                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
//            }
//    )
//    PersonDTO update(@RequestBody PersonDTO person);
//
//    @Operation( // SWAGGER
//            summary = "Delete Person by ID",
//            description = "Delete Person by ID",
//            tags = {"People"},
//            responses = {
//                    @ApiResponse(
//                            description = "Success",
//                            responseCode = "200",
//                            content = @Content(schema = @Schema(implementation = PersonDTO.class))
//                    ),
//                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
//                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
//                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
//                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
//                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
//            }
//    )
//    ResponseEntity<?> delete(@PathVariable("id") Long id);
//}
