package com.itemis.salestaxes.services.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ReceiptDTO {

    private ShoppingBasketDTO shoppingBasket;
    private BigDecimal salesTaxes;
    private BigDecimal total;
}


