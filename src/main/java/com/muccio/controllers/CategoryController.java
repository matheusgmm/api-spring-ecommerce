package com.muccio.controllers;

import com.muccio.models.dto.CategoryDTO;
import com.muccio.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Category", description = "Endpoint for view Categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    @Operation(summary = "Find all categories", description = "Find all categories",
        tags = { "Category" },
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class)))
            ),
            @ApiResponse(
                description = "No Content",
                responseCode = "204",
                content = @Content(array = @ArraySchema(schema = @Schema()))
            ),
            @ApiResponse(
                description = "Bad Request",
                responseCode = "400",
                content = @Content(array = @ArraySchema(schema = @Schema()))
            ),
            @ApiResponse(
                description = "Internal Server Error",
                responseCode = "500",
                content = @Content(array = @ArraySchema(schema = @Schema()))
            )
        }
    )
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }
}
