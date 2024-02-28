package com.forceofcollection.foc.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.forceofcollection.foc.entity.Card;

public interface CardRepository extends JpaRepository<Card, Integer>, JpaSpecificationExecutor<Card> {
    Optional<Card> findById(Integer id);
    Page<Card> findAll(Specification<Card> spec, Pageable pageable);
}
