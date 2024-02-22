package com.forceofcollection.foc.repository;

import java.util.function.Consumer;

import com.forceofcollection.foc.model.SearchCriteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {

    private Predicate predicate;
    private CriteriaBuilder builder;
    @SuppressWarnings("rawtypes")
    private Root r;

    @SuppressWarnings("unchecked")
    @Override
    public void accept(SearchCriteria param) {
        if (param.getOperation().equalsIgnoreCase(">")) {
            predicate = builder.and(predicate, builder
              .greaterThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));

        } else if (param.getOperation().equalsIgnoreCase("<")) {
            predicate = builder.and(predicate, builder.lessThanOrEqualTo(
              r.get(param.getKey()), param.getValue().toString()));

        } else if (param.getOperation().equalsIgnoreCase(":")) {

            if (r.get(param.getKey()).getJavaType() == String.class) {
                predicate = builder.and(predicate, builder.like(
                  builder.lower(r.get(param.getKey())), "%" + param.getValue() + "%"));
            } else {
                predicate = builder.and(predicate, builder.equal(
                  r.get(param.getKey()), param.getValue()));
            }
        }
    }
    
}
