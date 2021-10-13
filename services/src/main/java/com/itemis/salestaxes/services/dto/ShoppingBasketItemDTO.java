package com.itemis.salestaxes.services.dto;

import com.itemis.salestaxes.services.domain.SaleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppingBasketItemDTO {
    private ProductDTO product;
    private Double price;
    private Integer quantity;
    private SaleType saleType;



}
