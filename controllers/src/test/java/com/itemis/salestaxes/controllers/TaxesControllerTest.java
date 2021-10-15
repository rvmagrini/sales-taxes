package com.itemis.salestaxes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itemis.salestaxes.Application;
import com.itemis.salestaxes.services.domain.SaleType;
import com.itemis.salestaxes.services.domain.TaxExemptionType;
import com.itemis.salestaxes.services.dto.ProductDTO;
import com.itemis.salestaxes.services.dto.ShoppingBasketDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc
class TaxesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static ShoppingBasketDTO shoppingBasket;

    @BeforeAll
    private static void init() {
        shoppingBasket = ShoppingBasketDTO.builder()
                .item(
                        ProductDTO.builder()
                                .name("Bottle of Perfume")
                                .taxExemptionType(TaxExemptionType.TAXABLE)
                                .price(new BigDecimal("27.99"))
                                .quantity(new BigDecimal("1.00"))
                                .saleType(SaleType.IMPORTED)
                                .build()
                )
                .item(
                        ProductDTO.builder()
                                .name("Bottle of Perfume")
                                .taxExemptionType(TaxExemptionType.TAXABLE)
                                .price(new BigDecimal("18.99"))
                                .quantity(new BigDecimal("1.00"))
                                .saleType(SaleType.DOMESTIC)
                                .build()
                )
                .item(
                        ProductDTO.builder()
                                .name("Packet of Headache Pills")
                                .taxExemptionType(TaxExemptionType.EXEMPT)
                                .price(new BigDecimal("9.75"))
                                .quantity(new BigDecimal("1.00"))
                                .saleType(SaleType.DOMESTIC)
                                .build()
                )
                .item(
                        ProductDTO.builder()
                                .name("Box of Chocolate")
                                .taxExemptionType(TaxExemptionType.EXEMPT)
                                .price(new BigDecimal("11.25"))
                                .quantity(new BigDecimal("1.00"))
                                .saleType(SaleType.IMPORTED)
                                .build()
                )
                .build();
    }

    @Test
    public void givenShoppingBasketJson_whenCalculateTaxesPost_thenExpectedReceiptValuesMatch() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders
                .post("/taxes/calculate")
                .content(asJsonString(shoppingBasket))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.shoppingBasket.items[0].name").value("Bottle of Perfume"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shoppingBasket.items[0].price").value(32.19))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salesTaxes").value(6.70))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(74.68));
    }

    @Test
    public void givenShoppingBasketJson_whenCalculateTaxesPost_thenReceiptJsonExists() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders
                .post("/taxes/calculate")
                .content(asJsonString(shoppingBasket))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.shoppingBasket").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.salesTaxes").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}