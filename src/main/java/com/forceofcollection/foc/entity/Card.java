package com.forceofcollection.foc.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "cards")
public class Card {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    private String game_id;
    private String url_picture;
    private String name;
    private String cost;
    private String text;
    private Integer atk;
    private Integer def;
    private String flavour;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "set_id")
    private com.forceofcollection.foc.entity.Set set;

    @ManyToOne
    @JoinColumn(name = "rarity_id")
    private Rarity rarity;

    @ManyToMany
    @JoinTable(
        name = "card_attributes",
        joinColumns = @JoinColumn(name = "card_id"),
        inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private Set<Attribute> attributes;

    @ManyToMany
    @JoinTable(
        name = "card_races_traits",
        joinColumns = @JoinColumn(name = "card_id"),
        inverseJoinColumns = @JoinColumn(name = "race_trait_id")
    )
    private Set<RaceTrait> raceTraits;

    @JsonIgnore
    @OneToMany(mappedBy = "card")
    private Set<UserCard> userCards;
}
