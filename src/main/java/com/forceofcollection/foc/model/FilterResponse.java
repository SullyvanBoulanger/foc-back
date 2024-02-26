package com.forceofcollection.foc.model;

import java.util.List;

import com.forceofcollection.foc.entity.Attribute;
import com.forceofcollection.foc.entity.RaceTrait;
import com.forceofcollection.foc.entity.Rarity;
import com.forceofcollection.foc.entity.Set;
import com.forceofcollection.foc.entity.Type;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilterResponse {
    private List<Type> types;
    private List<Attribute> attributes;
    private List<RaceTrait> raceTraits;
    private List<Set> sets;
    private List<Rarity> rarities;
}
