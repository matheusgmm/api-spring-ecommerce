package com.muccio.models.dto;

import com.muccio.models.entities.OrderItem;

public class OrderItemDTO {

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;
    private String imgUrl;

    public OrderItemDTO(String name, Double price, Long productId, Integer quantity, String imgUrl) {
        this.name = name;
        this.price = price;
        this.productId = productId;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }

    public OrderItemDTO(OrderItem entity) {
        productId = entity.getProduct().getId();
        name = entity.getProduct().getName();
        price = entity.getPrice();
        quantity = entity.getQuantity();
        imgUrl = entity.getProduct().getImgUrl();

    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getSubtotal() {
        return price * quantity;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}