package com.example.News.Controller;

import com.example.News.Model.Article;
import com.example.News.Model.PaginationResponse;
import com.example.News.Service.ArticleService;
import com.example.News.Utils.PostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @Operation(summary = "Register an article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article registered successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request, please check Json file",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<?> addArticle(@RequestBody Article article) {
        final PostResponse postResponse = articleService.addArticle(article);
        return new ResponseEntity(postResponse.getUrl(), postResponse.getStatus());
    }

    @PutMapping("/{id}/writer/{writerId}")
    @Operation(summary = "Modify article's writer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article modified successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request, please check Json file",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    private ResponseEntity<?> addWriter(@PathVariable Integer id, @PathVariable Integer writerId) {
        final PostResponse postResponse = articleService.addWriter(id, writerId);
        return new ResponseEntity(postResponse.getUrl(), postResponse.getStatus());
    }

    @GetMapping
    @Operation(summary = "Get a paginated article list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article list returned successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request, please check Json file",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public PaginationResponse<Article> getAll(@RequestParam(value = "size", defaultValue = "10") Integer size,
                                              @RequestParam(value = "page", defaultValue = "0") Integer page) {
        return articleService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article returned successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request, please check Json file",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public Article getArticleById(@PathVariable Integer id) {
        return articleService.getArticleById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Article deleted successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request, please check Json file",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<?> deleteArticle(@PathVariable Integer id) {
        final PostResponse postResponse = articleService.deleteArticle(id);
        return new ResponseEntity(postResponse.getUrl(), postResponse.getStatus());
    }
}