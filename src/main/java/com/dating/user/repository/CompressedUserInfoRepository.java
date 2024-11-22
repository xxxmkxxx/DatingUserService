package com.dating.user.repository;

import com.dating.user.model.CompressedUserInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompressedUserInfoRepository extends JpaRepository<CompressedUserInfoModel, Integer> {
    List<CompressedUserInfoModel> findByHideAndGender(boolean isHide, boolean gender);
}
