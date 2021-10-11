package com.itemis.salestaxes.services.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingBasketDTO {

    private List<ShoppingBasketItemDTO> items = new ArrayList<>();


}
