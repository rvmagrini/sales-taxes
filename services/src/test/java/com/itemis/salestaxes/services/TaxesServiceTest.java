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
class TaxesServiceTest {

    private TaxesService service;

    @BeforeAll
    void setup() {
        service = new TaxesService();
    }


    @Test
    void givenShoppingBasket_whenCalculateTaxes_thenReturnReceipt() {
        ShoppingBasketDTO givenShoppingBasket =
                ShoppingBasketDTO.builder()
                        .item(
                                ShoppingBasketItemDTO.builder()
                                        .product(ProductDTO.builder().name("Book").build())
                                        .price(12.49)
                                        .quantity(1)
                                        .build()
                        )
                        .item(
                                ShoppingBasketItemDTO.builder()
                                        .product(ProductDTO.builder().name("Music CD").build())
                                        .price(14.99)
                                        .quantity(1)
                                        .build()
                        )
                        .item(
                                ShoppingBasketItemDTO.builder()
                                        .product(ProductDTO.builder().name("Chocolate Bar").build())
                                        .price(0.85)
                                        .quantity(1)
                                        .build()
                        )
                        .build();


        ReceiptDTO actualReceipt = service.calculateTaxes(givenShoppingBasket);
        ReceiptDTO expectedReceipt = ReceiptDTO.builder()
                .shoppingBasket(givenShoppingBasket)
                .salesTaxes(1.50)
                .total(29.83)
                .build();

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