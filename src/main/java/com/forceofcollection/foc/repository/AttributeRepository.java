package com.forceofcollection.foc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forceofcollection.foc.entity.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Integer>{
    
}
