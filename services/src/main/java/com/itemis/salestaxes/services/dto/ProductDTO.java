package com.itemis.salestaxes.services.dto;

import com.itemis.salestaxes.services.domain.SaleType;
import com.itemis.salestaxes.services.domain.TaxExemptionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDTO {
    private String name;
    private BigDecimal price;
    private BigDecimal quantity;
    private TaxExemptionType taxExemptionType;
    private SaleType saleType;

}
