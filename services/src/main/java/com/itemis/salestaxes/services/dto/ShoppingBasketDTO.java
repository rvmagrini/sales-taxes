package com.itemis.salestaxes.services.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingBasketDTO {

    @Singular
    private List<ProductDTO> items;


}
