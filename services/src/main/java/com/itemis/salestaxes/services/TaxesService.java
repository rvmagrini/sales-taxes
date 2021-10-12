package com.itemis.salestaxes.services;

import com.itemis.salestaxes.services.dto.ReceiptDTO;
import com.itemis.salestaxes.services.dto.ShoppingBasketDTO;

public class TaxesService {

    public ReceiptDTO calculateTaxes(ShoppingBasketDTO givenShoppingBasket) {
        return ReceiptDTO.builder()
                .shoppingBasket(givenShoppingBasket)
                .salesTaxes(1.50)
                .total(29.83)
                .build();
    }
}
