package com.itemis.salestaxes.services;

import com.itemis.salestaxes.services.domain.SaleType;
import com.itemis.salestaxes.services.domain.TaxExemptionType;
import com.itemis.salestaxes.services.dto.ReceiptDTO;
import com.itemis.salestaxes.services.dto.ShoppingBasketDTO;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class TaxesService {

    private Double baseTax = 10.0;
    private Double importTax = 5.0;

    public ReceiptDTO calculateTaxes(ShoppingBasketDTO givenShoppingBasket) {

        final Double[] total = {0.0};
        final Double[] totalTax = {0.0};

        givenShoppingBasket.getItems().forEach(
                item -> {
                    Double taxOnItem =
                            roundValue((item.getPrice() * (
                                    (item.getProduct().getTaxExemptionType() == TaxExemptionType.EXEMPT ? 0.0 : baseTax) +
                                    (item.getSaleType() == SaleType.IMPORTED ? (importTax) : 0.0))) / 100);
                    total[0] += (item.getPrice() * item.getQuantity()) + taxOnItem;
                    totalTax[0] += taxOnItem;
                    item.setPrice(twoDecimalPlaces(item.getPrice() + taxOnItem));
                }
        );

        return ReceiptDTO.builder()
                .shoppingBasket(givenShoppingBasket)
                .salesTaxes(twoDecimalPlaces(totalTax[0]))
                .total(twoDecimalPlaces(total[0]))
                .build();
    }

    private Double twoDecimalPlaces(Double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        decimalFormat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        return Double.parseDouble(decimalFormat.format(value));
    }

    private double roundValue(Double value) {
        return Math.round(value * 20) / 20.0;
    }

}
