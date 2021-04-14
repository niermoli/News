package com.example.News.Controller;

import com.example.News.Converter.WriterDTOConverter;
import com.example.News.Model.DTO.WriterDTO;
import com.example.News.Model.PaginationResponse;
import com.example.News.Model.Writer;
import com.example.News.Service.WriterService;
import com.example.News.Utils.PostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/writer")
public class WriterController {

    @Autowired
    private WriterService writerService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private WriterDTOConverter writerDTOConverter;

    @PostMapping
    @Operation(summary = "Register a writer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Writer registered successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request, please check Json file",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<?> addWriter(@RequestBody Writer writer) {
        final PostResponse postResponse = writerService.addWriter(writer);
        return new ResponseEntity(postResponse.getUrl(), postResponse.getStatus());
    }

    @PutMapping
    @Operation(summary = "Update a writer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Writer updated successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request, please check Json file",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    private ResponseEntity<?> updateWriter(@RequestBody Writer writer) {
        final PostResponse postResponse = writerService.updateWriter(writer);
        return new ResponseEntity(postResponse.getUrl(), postResponse.getStatus());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a writer by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Writer updated successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request, please check Json file",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    private ResponseEntity<?> updateWriter(@RequestParam Integer id, @RequestBody Writer writer) {
        final PostResponse postResponse = writerService.updateWriterById(id, writer);
        return new ResponseEntity(postResponse.getUrl(), postResponse.getStatus());
    }

    @GetMapping
    @Operation(summary = "Get a paginated writers' list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Writer list returned successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request, please check Json file",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public PaginationResponse<Writer> getAll(@RequestParam(value = "size", defaultValue = "10") Integer size,
                                             @RequestParam(value = "page", defaultValue = "0") Integer page) {
        return writerService.getAll(page, size);
    }

    @GetMapping("/writerDTO")
    @Operation(summary = "Writers' list DTO (without DNI)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Writer list returned successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request, please check Json file",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public List<WriterDTO> getAllWritersDTO(){
        return conversionService.convert(writerService.getAll(), List.class);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a writer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Writer returned successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request, please check Json file",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public Writer getWriterById(@PathVariable Integer id) {
        return writerService.getWriterById(id);
    }

    @GetMapping("/writerDTO/{id}")
    @Operation(summary = "Get a writer DTO by Id")
    public WriterDTO getWriterDTOById(@PathVariable Integer id) {
        return conversionService.convert(writerService.getWriterById(id), WriterDTO.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a writer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Writer deleted successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request, please check Json file",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<?> deleteWriter(@PathVariable Integer id) {
        final PostResponse postResponse = writerService.deleteWriter(id);
        return new ResponseEntity(postResponse.getUrl(), postResponse.getStatus());
    }

}
