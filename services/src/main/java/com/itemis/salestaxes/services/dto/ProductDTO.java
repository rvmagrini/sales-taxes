package com.itemis.salestaxes.services.dto;

import com.itemis.salestaxes.services.domain.TaxExemptionType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private String name;
    private TaxExemptionType taxExemptionType;

}
