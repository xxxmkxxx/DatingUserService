package com.dating.userinfo.repository;

import com.dating.userinfo.model.InterestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<InterestModel, Integer> {
    boolean existsByName(String name);
}
