package com.dating.userinfo.repository;

import com.dating.userinfo.model.UserInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoModel, Integer> {
}
