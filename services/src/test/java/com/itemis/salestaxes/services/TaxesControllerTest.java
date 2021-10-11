package com.itemis.salestaxes.services;

import com.itemis.salestaxes.services.dto.ProductDTO;
import com.itemis.salestaxes.services.dto.ReceiptDTO;
import com.itemis.salestaxes.services.dto.ShoppingBasketDTO;
import com.itemis.salestaxes.services.dto.ShoppingBasketItemDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaxesControllerTest {

    private TaxesService service;

    @BeforeAll
    void setup() {
        service = new TaxesService();
    }


    @Test
    void givenShoppingBasket_whenCalculateTaxes_thenReturnReceipt() {
        ShoppingBasketDTO givenShoppingBasket = new ShoppingBasketDTO();

        givenShoppingBasket.getItems().add(new ShoppingBasketItemDTO(new ProductDTO("Book"), 12.49, 1));
        givenShoppingBasket.getItems().add(new ShoppingBasketItemDTO (new ProductDTO("Music CD"), 14.99, 1));
        givenShoppingBasket.getItems().add(new ShoppingBasketItemDTO (new ProductDTO("Chocolate Bar"),0.85, 1));

        ReceiptDTO actualReceipt = service.calculateTaxes(givenShoppingBasket);
        ReceiptDTO expectedReceipt = new ReceiptDTO(givenShoppingBasket, 1.50, 29.83);

        Assertions.assertEquals(expectedReceipt, actualReceipt);


    }

//    Input 1:
//    > 1 book at 12.49
//    > 1 music CD at 14.99
//    > 1 chocolate bar at 0.85
//    Output 1:
//    > 1 book: 12.49
//    > 1 music CD: 16.49
//    > 1 chocolate bar: 0.85
//    > Sales Taxes: 1.50
//    > Total: 29.83


}