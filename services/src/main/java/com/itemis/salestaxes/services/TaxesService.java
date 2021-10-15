package com.itemis.salestaxes.services;

import com.itemis.salestaxes.services.domain.SaleType;
import com.itemis.salestaxes.services.domain.TaxExemptionType;
import com.itemis.salestaxes.services.dto.ReceiptDTO;
import com.itemis.salestaxes.services.dto.ShoppingBasketDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TaxesService {

    private BigDecimal baseTax = new BigDecimal("10.00");
    private BigDecimal importTax = new BigDecimal("5.00");

    public ReceiptDTO calculateTaxes(ShoppingBasketDTO givenShoppingBasket) {

        final BigDecimal[] total = {BigDecimal.ZERO};
        final BigDecimal[] totalTax = {BigDecimal.ZERO};

        givenShoppingBasket.getItems().forEach(
                item -> {
                    BigDecimal taxOnItem =
                            roundValue(item.getPrice()
                                    .multiply((item.getTaxExemptionType() == TaxExemptionType.EXEMPT ? BigDecimal.ZERO : baseTax)
                                    .add(item.getSaleType() == SaleType.IMPORTED ? importTax : BigDecimal.ZERO))
                                    .divide(new BigDecimal("100.00")));

                    total[0] = total[0].add(item.getPrice().multiply(item.getQuantity()).add(taxOnItem));
                    totalTax[0] = totalTax[0].add(taxOnItem);
                    item.setPrice(twoDecimalPlaces(item.getPrice().add(taxOnItem)));
                }
        );

        return ReceiptDTO.builder()
                .shoppingBasket(givenShoppingBasket)
                .salesTaxes(twoDecimalPlaces(totalTax[0]))
                .total(twoDecimalPlaces(total[0]))
                .build();
    }

    private BigDecimal twoDecimalPlaces(BigDecimal value) {
        return value.setScale(2, RoundingMode.CEILING);
    }

    private BigDecimal roundValue(BigDecimal value) {
                // Divide by 20
        return value.multiply(new BigDecimal("20.00"))
                // Round up to the nearest integer
                .setScale(0, RoundingMode.UP)
                // Then divide by 20
                .divide(new BigDecimal("20.00"));
    }

}
