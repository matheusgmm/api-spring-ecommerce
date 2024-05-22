package com.muccio.controllers;


import com.muccio.models.dto.OrderDTO;
import com.muccio.models.dto.UserDTO;
import com.muccio.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "Orders", description = "Endpoint for Managing Orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
    @GetMapping(value = "/{id}")
    @Operation(summary = "Order information's" ,description = "Return information's about selected order",
        tags = { "Orders" },
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))
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
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
        OrderDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping
    @Operation(summary = "New Order" ,description = "Create new order",
        tags = { "Orders" },
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))
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
    public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
