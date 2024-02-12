package com.forceofcollection.foc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(name = "races_traits", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class RaceTrait {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    private String name;
}
