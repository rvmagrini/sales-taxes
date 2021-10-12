package com.itemis.salestaxes.services.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReceiptDTO {

    private ShoppingBasketDTO shoppingBasket;
    private Double salesTaxes;
    private Double total;
}


