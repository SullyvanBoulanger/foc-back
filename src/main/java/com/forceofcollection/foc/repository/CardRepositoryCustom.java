package com.forceofcollection.foc.repository;

import java.util.List;

import com.forceofcollection.foc.entity.Card;
import com.forceofcollection.foc.model.CardPreview;
import com.forceofcollection.foc.model.SearchCriteria;

public interface CardRepositoryCustom {
    public List<CardPreview> searchCard(List<SearchCriteria> params);
    public Card save(Card entity);
}
