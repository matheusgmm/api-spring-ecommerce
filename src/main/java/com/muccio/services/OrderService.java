package com.muccio.services;

import com.muccio.models.dto.OrderDTO;
import com.muccio.models.entities.Order;
import com.muccio.repositories.OrderRepository;
import com.muccio.services.exceptions.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado."));
        return new OrderDTO(order);
    }
}
