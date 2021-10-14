package com.itemis.salestaxes.services;

import com.itemis.salestaxes.services.domain.SaleType;
import com.itemis.salestaxes.services.domain.TaxExemptionType;
import com.itemis.salestaxes.services.dto.ProductDTO;
import com.itemis.salestaxes.services.dto.ReceiptDTO;
import com.itemis.salestaxes.services.dto.ShoppingBasketDTO;
import com.itemis.salestaxes.services.dto.ShoppingBasketItemDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaxesServiceTest {

    private TaxesService service;

    @BeforeAll
    void setup() {
        service = new TaxesService();
    }

    @Test
    void givenShoppingBasketWithTwoImportedItems_whenCalculateTaxes_thenReturnReceipt() {
        ShoppingBasketDTO givenShoppingBasket =
                ShoppingBasketDTO.builder()
                        .item(
                                ShoppingBasketItemDTO.builder()
                                        .product(ProductDTO.builder()
                                                .name("Box of Chocolates")
                                                .taxExemptionType(TaxExemptionType.EXEMPT)
                                                .build())
                                        .price(new BigDecimal("10.00"))
                                        .quantity(new BigDecimal("1.00"))
                                        .saleType(SaleType.IMPORTED)
                                        .build()
                        )
                        .item(
                                ShoppingBasketItemDTO.builder()
                                        .product(ProductDTO.builder()
                                                .name("Bottle of Perfume")
                                                .build())
                                        .price(new BigDecimal("47.50"))
                                        .quantity(new BigDecimal("1.00"))
                                        .saleType(SaleType.IMPORTED)
                                        .build()
                        )
                        .build();

        ReceiptDTO actualReceipt = service.calculateTaxes(givenShoppingBasket);
        ReceiptDTO expectedReceipt =
                ReceiptDTO.builder()
                        .shoppingBasket(
                                ShoppingBasketDTO.builder()
                                        .item(
                                                ShoppingBasketItemDTO.builder()
                                                        .product(ProductDTO.builder()
                                                                .name("Box of Chocolates")
                                                                .taxExemptionType(TaxExemptionType.EXEMPT)
                                                                .build())
                                                        .price(new BigDecimal("10.50"))
                                                        .quantity(new BigDecimal("1.00"))
                                                        .saleType(SaleType.IMPORTED)
                                                        .build()
                                        )
                                        .item(
                                                ShoppingBasketItemDTO.builder()
                                                        .product(ProductDTO.builder()
                                                                .name("Bottle of Perfume")
                                                                .build())
                                                        .price(new BigDecimal("54.65"))
                                                        .quantity(new BigDecimal("1.00"))
                                                        .saleType(SaleType.IMPORTED)
                                                        .build()
                                        )
                                        .build()
                        )
                        .salesTaxes(new BigDecimal("7.65"))
                        .total(new BigDecimal("65.15"))
                        .build();

        Assertions.assertEquals(expectedReceipt, actualReceipt);

    }


    @Test
    void givenShoppingBasketWithThreeItems_whenCalculateTaxes_thenReturnReceipt() {
        ShoppingBasketDTO givenShoppingBasket =
                ShoppingBasketDTO.builder()
                        .item(
                                ShoppingBasketItemDTO.builder()
                                        .product(ProductDTO.builder()
                                                .name("Book")
                                                .taxExemptionType(TaxExemptionType.EXEMPT)
                                                .build())
                                        .price(new BigDecimal("12.49"))
                                        .quantity(new BigDecimal("1.00"))
                                        .build()
                        )
                        .item(
                                ShoppingBasketItemDTO.builder()
                                        .product(ProductDTO.builder()
                                                .name("Music CD")
                                                .build())
                                        .price(new BigDecimal("14.99"))
                                        .quantity(new BigDecimal("1.00"))
                                        .build()
                        )
                        .item(
                                ShoppingBasketItemDTO.builder()
                                        .product(ProductDTO.builder()
                                                .name("Chocolate Bar")
                                                .taxExemptionType(TaxExemptionType.EXEMPT)
                                                .build())
                                        .price(new BigDecimal("0.85"))
                                        .quantity(new BigDecimal("1.00"))
                                        .build()
                        )
                        .build();


        ReceiptDTO actualReceipt = service.calculateTaxes(givenShoppingBasket);
        ReceiptDTO expectedReceipt = ReceiptDTO.builder()
                .shoppingBasket(
                        ShoppingBasketDTO.builder()
                        .item(
                                ShoppingBasketItemDTO.builder()
                                        .product(ProductDTO.builder()
                                                .name("Book")
                                                .taxExemptionType(TaxExemptionType.EXEMPT)
                                                .build())
                                        .price(new BigDecimal("12.49"))
                                        .quantity(new BigDecimal("1.00"))
                                        .build()
                        )
                        .item(
                                ShoppingBasketItemDTO.builder()
                                        .product(ProductDTO.builder()
                                                .name("Music CD")
                                                .build())
                                        .price(new BigDecimal("16.49"))
                                        .quantity(new BigDecimal("1.00"))
                                        .build()
                        )
                        .item(
                                ShoppingBasketItemDTO.builder()
                                        .product(ProductDTO.builder()
                                                .name("Chocolate Bar")
                                                .taxExemptionType(TaxExemptionType.EXEMPT)
                                                .build())
                                        .price(new BigDecimal("0.85"))
                                        .quantity(new BigDecimal("1.00"))
                                        .build()
                        )
                        .build()
                )
                .salesTaxes(new BigDecimal("1.50"))
                .total(new BigDecimal("29.83"))
                .build();

        Assertions.assertEquals(expectedReceipt, actualReceipt);

    }

//    ### INPUT:
//    Input 1:
//    > 1 book at 12.49
//    > 1 music CD at 14.99
//    > 1 chocolate bar at 0.85
//
//    Input 2:
//    > 1 imported box of chocolates at 10.00
//    > 1 imported bottle of perfume at 47.50
//
//    Input 3:
//    > 1 imported bottle of perfume at 27.99
//    > 1 bottle of perfume at 18.99
//    > 1 packet of headache pills at 9.75
//    > 1 box of imported chocolates at 11.25
//
//    ### OUTPUT
//
//    Output 1:
//    > 1 book: 12.49
//    > 1 music CD: 16.49
//    > 1 chocolate bar: 0.85
//    > Sales Taxes: 1.50
//    > Total: 29.83
//
//    Output 2:
//    > 1 imported box of chocolates: 10.50
//    > 1 imported bottle of perfume: 54.65
//    > Sales Taxes: 7.65
//    > Total: 65.15
//
//    Output 3:
//    > 1 imported bottle of perfume: 32.19
//    > 1 bottle of perfume: 20.89 > 1 packet of headache pills: 9.75 > 1 imported box of chocolates: 11.85 > Sales Taxes: 6.70 > Total: 74.68


}