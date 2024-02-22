package com.forceofcollection.foc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forceofcollection.foc.entity.Card;

public interface CardRepository extends JpaRepository<Card, Integer>, CardRepositoryCustom {
    Optional<Card> findById(Integer id);
}
