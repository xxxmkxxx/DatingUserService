package com.dating.userinfo.repository;

import com.dating.userinfo.model.BadHabitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadHabitRepository extends JpaRepository<BadHabitModel, Integer> {
    boolean existsByName(String name);
}
