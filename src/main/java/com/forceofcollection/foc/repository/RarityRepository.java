package com.forceofcollection.foc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forceofcollection.foc.entity.Rarity;

public interface RarityRepository extends JpaRepository<Rarity, Integer> {
    
}
