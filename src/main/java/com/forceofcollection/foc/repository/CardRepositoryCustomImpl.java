package com.forceofcollection.foc.repository;

import java.util.List;

import com.forceofcollection.foc.entity.Card;
import com.forceofcollection.foc.model.CardPreview;
import com.forceofcollection.foc.model.SearchCriteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CardRepositoryCustomImpl implements CardRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CardPreview> searchCard(List<SearchCriteria> params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Card> query = builder.createQuery(Card.class);
        @SuppressWarnings("rawtypes")
        Root root = query.from(Card.class);

        Predicate predicate = builder.conjunction();

        CardSearchQueryCriteriaConsumer searchConsumer =
            new CardSearchQueryCriteriaConsumer(predicate, builder, root);
        params.stream().forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

        List<Card> queryResult = entityManager.createQuery(query).getResultList();

        List<CardPreview> cardPreviews = queryResult.stream().map(card -> 
            new CardPreview(card.getId(), card.getUrl_picture())
        ).toList();
        
        return cardPreviews;
    }

    @Override
    public Card save(Card entity) {
        entityManager.persist(entity);
        return entity;
    }
    
}
