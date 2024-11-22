package com.dating.user.service;

import com.dating.user.data.CompressedInfoFilterData;
import com.dating.user.data.CompressedUserInfoData;
import com.dating.user.data.ResponseData;
import com.dating.user.data.UserInfoData;
import com.dating.user.model.CompressedUserInfoModel;
import com.dating.user.model.UserInfoModel;
import com.dating.user.model.UserModel;
import com.dating.user.repository.CompressedUserInfoRepository;
import com.dating.user.repository.UserInfoRepository;
import com.dating.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserInfoRepository userInfoRepository;
    private CompressedUserInfoRepository compressedUserInfoRepository;
    private UserRepository userRepository;

    public ResponseData<List<CompressedUserInfoData>> getCompressedUserInfos(CompressedInfoFilterData filter) {
        List<CompressedUserInfoModel> result;

        if (filter.isAllInfos() && filter.isShowHidden()) {
            result = compressedUserInfoRepository.findAll();
        } else {
            result = compressedUserInfoRepository.findByHideAndGender(false, filter.isGender());
        }

        return new ResponseData<>(
                true, null,
                result.stream()
                        .map(CompressedUserInfoData::new)
                        .toList()
        );
    }

    public ResponseData<UserInfoData> getUserInfoByLogin(String login) {
        if (userRepository.existsByUserLogin(login)) {
            return new ResponseData<>(
                    true,
                    null,
                    new UserInfoData(userRepository.findByUserLogin(login).getUserInfo())
            );
        }

        return new ResponseData<>(false, "No user information found for this login!", null);
    }

    public ResponseData<Void> changeCompressedUserInfo(String login, CompressedUserInfoData data) {
        if (!userRepository.existsByUserLogin(login)) {
            return new ResponseData<>(false, "User with such login not found!", null);
        }

        UserModel user = userRepository.findByUserLogin(login);
        CompressedUserInfoModel compressedUserInfo = user.getUserInfo().getCompressedUserInfo();

        compressedUserInfo.setName(data.getName());
        compressedUserInfo.setSurname(data.getSurname());
        compressedUserInfo.setAge(data.getAge());
        compressedUserInfo.setIconPath(data.getIconPath());
        compressedUserInfo.setBriefDescription(data.getBriefDescription());
        compressedUserInfo.setGender(data.isGender());

        userRepository.save(user);

        return new ResponseData<>(true, "User information updated successfully!", null);
    }

    public ResponseData<Void> createUser(String login, CompressedUserInfoData data) {
        if (userRepository.existsByUserLogin(login)) {
            return new ResponseData<>(false, "User with this login already exists!", null);
        }

        userRepository.save(createUserModel(login, data));

        return new ResponseData<>(true, "User added successfully!", null);
    }

    private CompressedUserInfoModel createCompressedUserInfo(CompressedUserInfoData data) {
        CompressedUserInfoModel compressedUserInfo = new CompressedUserInfoModel();
        compressedUserInfo.setName(data.getName());
        compressedUserInfo.setSurname(data.getSurname());
        compressedUserInfo.setAge(data.getAge());
        compressedUserInfo.setBriefDescription(data.getBriefDescription());
        compressedUserInfo.setIconPath(data.getIconPath());
        compressedUserInfo.setGender(data.isGender());

        return compressedUserInfo;
    }

    private UserModel createUserModel(String login, CompressedUserInfoData data) {
        CompressedUserInfoModel compressedUserInfo = compressedUserInfoRepository.save(createCompressedUserInfo(data));
        UserInfoModel userInfo = userInfoRepository.save(new UserInfoModel());
        UserModel user = new UserModel();
        user.setUserLogin(login);

        userInfo.setUser(user);
        user.setUserInfo(userInfo);

        userInfo.setCompressedUserInfo(compressedUserInfo);
        compressedUserInfo.setUserInfo(userInfo);

        return user;
    }
}
