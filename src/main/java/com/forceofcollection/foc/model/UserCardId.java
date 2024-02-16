package com.forceofcollection.foc.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class UserCardId implements Serializable {
    private Long user_id;
    private Long card_id;
}
