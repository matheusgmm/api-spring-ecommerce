package com.muccio.controllers;

import com.muccio.models.dto.ProductDTO;
import com.muccio.services.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(@RequestParam(name = "name", defaultValue = "") String name, Pageable pageable) {
        Page<ProductDTO> dto = service.findAll(name, pageable);
        return ResponseEntity.ok(dto);
    }

}
