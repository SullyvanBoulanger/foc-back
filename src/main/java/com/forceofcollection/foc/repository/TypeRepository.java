package com.forceofcollection.foc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forceofcollection.foc.entity.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {
    
}
