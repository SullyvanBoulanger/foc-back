package com.forceofcollection.foc.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@Table(name = "user_cards")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCard {
    @EmbeddedId
    @NonNull
    private UserCardId id;

    @MapsId("user_id")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId("card_id")
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @NonNull
    private Integer quantity;
}
