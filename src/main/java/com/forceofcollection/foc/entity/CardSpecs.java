package com.forceofcollection.foc.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;

public class CardSpecs {
    public static Specification<Card> nameLike(String searchedName) {
        return (root, query, builder) -> {
            if (searchedName == null || searchedName.isEmpty()) {
                return builder.conjunction();
            }

            Expression<String> lowerCaseName = builder.lower(root.get(Card_.NAME));
            Expression<String> lowerCaseSearch = builder.lower(builder.literal("%" + searchedName + "%"));
            return builder.like(lowerCaseName, lowerCaseSearch);
        };
    }

    public static Specification<Card> atkLike(String searchedAtk) {
        return (root, query, builder) -> {
            if (searchedAtk == null || searchedAtk.isEmpty()) {
                return builder.conjunction();
            }

            char operator = searchedAtk.charAt(0);
            Integer atk = Integer.parseInt(searchedAtk.substring(1));

            switch (operator) {
                case ':':
                    return builder.equal(root.get(Card_.ATK), atk);
                case '<':
                    return builder.lessThan(root.get(Card_.ATK), atk);
                case '>':
                    return builder.greaterThan(root.get(Card_.ATK), atk);
                default:
                   return builder.conjunction();
            }
        };
    }

    public static Specification<Card> defLike(String searchedDef) {
        return (root, query, builder) -> {
            if (searchedDef == null || searchedDef.isEmpty()) {
                return builder.conjunction();
            }

            char operator = searchedDef.charAt(0);
            Integer def = Integer.parseInt(searchedDef.substring(1));

            switch (operator) {
                case ':':
                    return builder.equal(root.get(Card_.DEF), def);
                case '<':
                    return builder.lessThan(root.get(Card_.DEF), def);
                case '>':
                    return builder.greaterThan(root.get(Card_.DEF), def);
                default:
                   return builder.conjunction();
            }
        };
    }

    public static Specification<Card> typeLike(String searchedType) {
        return (root, query, builder) -> {
            if (searchedType == null || searchedType.isEmpty()) {
                return builder.conjunction();
            }

            String[] types = searchedType.split(",");
            List<Predicate> predicates = new ArrayList<>();
            for (String type : types) {
                Expression<String> lowerCaseTypeName = builder.lower(root.join("type").get("name"));
                Expression<String> lowerCaseSearch = builder.lower(builder.literal(type.trim()));
                predicates.add(builder.like(lowerCaseTypeName, lowerCaseSearch));
            }
            return builder.or(predicates.toArray(new Predicate[0]));
        };
    }
    
    public static Specification<Card> setLike(String searchedSet) {
        return (root, query, builder) -> {
            if (searchedSet == null || searchedSet.isEmpty()) {
                return builder.conjunction();
            }

            String[] sets = searchedSet.split(",");
            List<Predicate> predicates = new ArrayList<>();
            for (String set : sets) {
                Expression<String> lowerCaseTypeName = builder.lower(root.join("set").get("abbreviation"));
                Expression<String> lowerCaseSearch = builder.lower(builder.literal(set.trim()));
                predicates.add(builder.like(lowerCaseTypeName, lowerCaseSearch));
            }
            return builder.or(predicates.toArray(new Predicate[0]));
        };
    }
    
    public static Specification<Card> rarityLike(String searchedRarity) {
        return (root, query, builder) -> {
            if (searchedRarity == null || searchedRarity.isEmpty()) {
                return builder.conjunction();
            }

            String[] rarities = searchedRarity.split(",");
            List<Predicate> predicates = new ArrayList<>();
            for (String rarity : rarities) {
                Expression<String> lowerCaseTypeName = builder.lower(root.join("rarity").get("code"));
                Expression<String> lowerCaseSearch = builder.lower(builder.literal(rarity.trim()));
                predicates.add(builder.like(lowerCaseTypeName, lowerCaseSearch));
            }
            return builder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Card> raceTraitLike(String searchedRaceTraits) {
        return (root, query, builder) -> {
            if (searchedRaceTraits == null || searchedRaceTraits.isEmpty()) {
                return builder.conjunction();
            }

            String[] traits = searchedRaceTraits.split(",");
            List<Predicate> predicates = new ArrayList<>();
            for (String trait : traits) {
                Expression<String> lowerCaseTypeName = builder.lower(root.join("raceTraits").get("name"));
                Expression<String> lowerCaseSearch = builder.lower(builder.literal(trait.trim()));
                predicates.add(builder.like(lowerCaseTypeName, lowerCaseSearch));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Card> attributesLike(String searchedAttribute) {
        return (root, query, builder) -> {
            if(searchedAttribute == null || searchedAttribute.isEmpty()){
                return builder.conjunction();
            }

            String[] attributes = searchedAttribute.split(",");
            List<Predicate> predicates = new ArrayList<>();

            for (String attribute : attributes) {
                Expression<String> lowerCaseTypeName = builder.lower(root.join("attributes").get("name"));
                Expression<String> lowerCaseSearch = builder.lower(builder.literal(attribute.trim()));
                predicates.add(builder.like(lowerCaseTypeName, lowerCaseSearch));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
