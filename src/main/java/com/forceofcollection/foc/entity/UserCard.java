package com.forceofcollection.foc.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_cards")
public class UserCard {
    @EmbeddedId
    private UserCardId id;

    @MapsId("user_id")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId("card_id")
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    private int quantity;
}
