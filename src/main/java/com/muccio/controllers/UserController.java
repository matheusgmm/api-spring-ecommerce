package com.muccio.controllers;

import com.muccio.models.dto.UserDTO;
import com.muccio.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "Endpoint for User information's")
public class UserController {

    @Autowired
    private UserService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
    @GetMapping(value = "/me")
    @Operation(summary = "User information's" ,description = "Return information's about logged user",
        tags = { "Users" },
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))
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
    public ResponseEntity<UserDTO> getMe() {
        UserDTO dto = service.getMe();
        return ResponseEntity.ok(dto);
    }
}
