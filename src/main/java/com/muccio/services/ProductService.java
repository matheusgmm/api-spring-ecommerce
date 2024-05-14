package com.muccio.services;


import com.muccio.models.dto.ProductDTO;
import com.muccio.models.entities.Product;
import com.muccio.repositories.ProductRepository;
import com.muccio.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDTO findById(Long id) {
        Product product =  productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource Not Found."));
        return new ProductDTO(product);
    }
}
