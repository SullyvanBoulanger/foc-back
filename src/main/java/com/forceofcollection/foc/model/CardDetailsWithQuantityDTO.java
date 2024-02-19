package com.forceofcollection.foc.model;

import com.forceofcollection.foc.entity.Card;

public record CardDetailsWithQuantityDTO(
    Card card,
    Integer quantity
) {}
