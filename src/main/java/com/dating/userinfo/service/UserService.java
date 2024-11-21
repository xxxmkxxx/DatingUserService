package com.dating.userinfo.service;

import com.dating.userinfo.data.CompressedUserInfoData;
import com.dating.userinfo.data.ResponseData;
import com.dating.userinfo.model.CompressedUserInfoModel;
import com.dating.userinfo.model.UserInfoModel;
import com.dating.userinfo.model.UserModel;
import com.dating.userinfo.repository.CompressedUserInfoRepository;
import com.dating.userinfo.repository.UserInfoRepository;
import com.dating.userinfo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
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

    public ResponseData<Void> createUser(String login, CompressedUserInfoData data) {
        if (userRepository.existsByUserLogin(login)) {
            return new ResponseData<>(false, "User with this login already exists!", null);
        }

        userRepository.save(createUserModel(login, data));

        return new ResponseData<>(true, "User added successfully!", null);
    }

    private static UserModel createUserModel(String login, CompressedUserInfoData data) {
        CompressedUserInfoModel compressedUserInfo = new CompressedUserInfoModel();
        compressedUserInfo.setName(data.getName());
        compressedUserInfo.setSurname(data.getSurname());
        compressedUserInfo.setAge(data.getAge());
        compressedUserInfo.setBriefDescription(data.getBriefDescription());
        compressedUserInfo.setIconPath(data.getIconPath());
        compressedUserInfo.setGender(data.isGender());

        UserInfoModel userInfo = new UserInfoModel();
        userInfo.setCompressedUserInfo(compressedUserInfo);

        UserModel user = new UserModel();
        user.setUserInfo(userInfo);
        user.setUserLogin(login);

        return user;
    }
}
