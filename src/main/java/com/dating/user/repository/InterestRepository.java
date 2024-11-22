package com.dating.user.repository;

import com.dating.user.model.InterestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<InterestModel, Integer> {
    boolean existsByName(String name);
}
