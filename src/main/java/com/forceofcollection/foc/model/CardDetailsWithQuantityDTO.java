package com.forceofcollection.foc.model;

import com.forceofcollection.foc.entity.Card;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardDetailsWithQuantityDTO{
    private Card card;
    private Integer quantity;
}
