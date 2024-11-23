package com.dating.user.repository;

import com.dating.user.model.UserInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoModel, Integer> {
    Optional<UserInfoModel> findByUserLogin(String login);
}
