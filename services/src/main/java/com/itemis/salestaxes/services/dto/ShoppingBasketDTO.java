package com.itemis.salestaxes.services.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class ShoppingBasketDTO {

    @Singular
    private List<ShoppingBasketItemDTO> items;


}
