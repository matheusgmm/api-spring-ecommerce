package com.muccio.controllers;

import com.muccio.models.dto.OrderDTO;
import com.muccio.models.dto.ProductDTO;
import com.muccio.models.dto.ProductMinDTO;
import com.muccio.models.dto.UserDTO;
import com.muccio.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "Endpoint for Managing Products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find all Products by ID" ,description = "Find all Products by ID",
        tags = { "Products" },
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))
            ),
            @ApiResponse(
                description = "Not Found",
                responseCode = "404",
                content = @Content(array = @ArraySchema(schema = @Schema()))
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
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @Operation(summary = "Find all Products" ,description = "Find all Products",
        tags = { "Products" },
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))
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
    public ResponseEntity<Page<ProductMinDTO>> findAll(@RequestParam(name = "name", defaultValue = "") String name, Pageable pageable) {
        Page<ProductMinDTO> dto = service.findAll(name, pageable);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "New Product" ,description = "Create a New Product",
        tags = { "Products" },
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))
            ),
            @ApiResponse(
                description = "Unauthorized",
                responseCode = "401",
                content = @Content(array = @ArraySchema(schema = @Schema()))
            ),
            @ApiResponse(
                description = "Forbidden",
                responseCode = "403",
                content = @Content(array = @ArraySchema(schema = @Schema()))
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
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    @Operation(summary = "Updates a Product" ,description = "Updates a Product's information",
        tags = { "Products" },
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))
            ),
            @ApiResponse(
                description = "Not Found",
                responseCode = "404",
                content = @Content(array = @ArraySchema(schema = @Schema()))
            ),
            @ApiResponse(
                description = "Unauthorized",
                responseCode = "401",
                content = @Content(array = @ArraySchema(schema = @Schema()))
            ),
            @ApiResponse(
                description = "Forbidden",
                responseCode = "403",
                content = @Content(array = @ArraySchema(schema = @Schema()))
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
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid  @RequestBody ProductDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Product" ,description = "Deletes a Product",
        tags = { "Products" },
        responses = {
            @ApiResponse(
                description = "No Content",
                responseCode = "204",
                content = @Content(array = @ArraySchema(schema = @Schema()))
            ),
            @ApiResponse(
                description = "Not Found",
                responseCode = "404",
                content = @Content(array = @ArraySchema(schema = @Schema()))
            ),
            @ApiResponse(
                description = "Unauthorized",
                responseCode = "401",
                content = @Content(array = @ArraySchema(schema = @Schema()))
            ),
            @ApiResponse(
                description = "Forbidden",
                responseCode = "403",
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
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
