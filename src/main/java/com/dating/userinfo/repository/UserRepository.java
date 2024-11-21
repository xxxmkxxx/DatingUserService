package com.dating.userinfo.repository;

import com.dating.userinfo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByUserLogin(String login);

    boolean existsByUserLogin(String login);
}
