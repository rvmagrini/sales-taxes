package com.itemis.salestaxes.controllers;

import com.itemis.salestaxes.services.TaxesService;
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

    @RequestMapping(value = "//taxes/calculate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ReceiptDTO calculateTaxes(@RequestBody ShoppingBasketDTO shoppingBasket) {

        return service.calculateTaxes(shoppingBasket);
    }


}
