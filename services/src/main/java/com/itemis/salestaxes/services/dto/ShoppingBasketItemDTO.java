package com.itemis.salestaxes.services.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppingBasketItemDTO {
    private ProductDTO product;
    private Double price;
    private Integer quantity;



}
