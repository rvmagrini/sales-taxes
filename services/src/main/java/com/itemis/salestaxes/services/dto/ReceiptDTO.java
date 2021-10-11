package com.itemis.salestaxes.services.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReceiptDTO {

    private ShoppingBasketDTO shoppingBasket;

    @EqualsAndHashCode.Include
    private Double salesTaxes;

    @EqualsAndHashCode.Include
    private Double total;
}


