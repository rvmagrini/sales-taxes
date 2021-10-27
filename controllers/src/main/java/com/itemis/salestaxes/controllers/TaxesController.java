package com.itemis.salestaxes.controllers;

import com.itemis.salestaxes.services.TaxesService;
import com.itemis.salestaxes.services.dto.ProductDTO;
import com.itemis.salestaxes.services.dto.ReceiptDTO;
import com.itemis.salestaxes.services.dto.ShoppingBasketDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaxesController {

    private TaxesService service;

    public TaxesController(TaxesService service) {
        this.service = service;
    }

    @RequestMapping(value = "/taxes/calculate",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ReceiptDTO calculateTaxes(@RequestBody ShoppingBasketDTO shoppingBasket) {
        ReceiptDTO receiptDTO = service.calculateTaxes(shoppingBasket);
        printOutReceiptDetails(receiptDTO);
        return receiptDTO;
    }

    private void printOutReceiptDetails(ReceiptDTO receiptDTO) {
        System.out.println("============== RECEIPT ==============");
        System.out.println("=====================================");
        System.out.println("Qt: Product ................... Price");

        for (ProductDTO product : receiptDTO.getShoppingBasket().getItems()) {
            System.out.print(product.getQuantity() + " : ");
            System.out.print(product.getName() + " : $");
            System.out.println(product.getPrice());
        }

        System.out.println("=====================================");
        System.out.println("> Sales Taxes: $" + receiptDTO.getSalesTaxes());
        System.out.println("> Total: $" + receiptDTO.getTotal());
        System.out.println("=====================================");
    }


}
