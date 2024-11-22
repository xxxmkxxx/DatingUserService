package com.dating.user.repository;

import com.dating.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByUserLogin(String login);

    boolean existsByUserLogin(String login);
}
