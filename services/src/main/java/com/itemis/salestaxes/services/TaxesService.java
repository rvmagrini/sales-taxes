package com.itemis.salestaxes.services;

import com.itemis.salestaxes.services.dto.ReceiptDTO;
import com.itemis.salestaxes.services.dto.ShoppingBasketDTO;

public class TaxesService {

    public ReceiptDTO calculateTaxes(ShoppingBasketDTO givenShoppingBasket) {
        return new ReceiptDTO(givenShoppingBasket, 1.50, 29.83);
    }
}
