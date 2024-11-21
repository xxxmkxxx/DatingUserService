package com.dating.userinfo.service;

import com.dating.userinfo.model.CompressedUserInfoModel;
import com.dating.userinfo.model.UserInfoModel;
import com.dating.userinfo.repository.CompressedUserInfoRepository;
import com.dating.userinfo.repository.UserInfoRepository;
import com.dating.userinfo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserInfoService {
    private UserInfoRepository userInfoRepository;
    private CompressedUserInfoRepository compressedUserInfoRepository;
    private UserRepository userRepository;

    public List<CompressedUserInfoModel> getAllCompressedUserInfos() {
        return compressedUserInfoRepository.findAll();
    }

    public Optional<UserInfoModel> getUserInfoByLogin(String login) {
        if (userRepository.existsByUserLogin(login)) {
            return Optional.of(userRepository.findByUserLogin(login).getUserInfo());
        }

        return Optional.empty();
    }
}
