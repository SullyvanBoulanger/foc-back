package com.forceofcollection.foc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forceofcollection.foc.entity.UserCard;
import com.forceofcollection.foc.entity.UserCardId;


public interface UserCardRepository extends JpaRepository<UserCard, UserCardId> {
    Optional<UserCard> findById(UserCardId id);
    List<UserCard> findByUser_Id(Integer userId);
}
