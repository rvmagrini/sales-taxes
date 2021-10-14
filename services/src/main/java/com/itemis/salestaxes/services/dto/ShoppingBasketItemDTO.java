package com.itemis.salestaxes.services.dto;

import com.itemis.salestaxes.services.domain.SaleType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ShoppingBasketItemDTO {
    private ProductDTO product;
    private BigDecimal price;
    private BigDecimal quantity;
    private SaleType saleType;



}
