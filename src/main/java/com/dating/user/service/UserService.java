package com.dating.user.service;

import com.dating.user.data.CompressedUserInfoData;
import com.dating.user.data.ResponseData;
import com.dating.user.model.CompressedUserInfoModel;
import com.dating.user.model.UserInfoModel;
import com.dating.user.model.UserModel;
import com.dating.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserInfoService userInfoService;
    private CompressedUserInfoService compressedUserInfoService;
    private UserRepository repository;

    public boolean existsUser(String login) {
        return repository.existsByLogin(login);
    }

    public ResponseData<UserModel> getUser(String login) {
        Optional<UserModel> userOptional = repository.findByLogin(login);

        return userOptional
                .map(userModel -> new ResponseData<>(true, null, userModel))
                .orElseGet(() -> new ResponseData<>(false, "User not found!", null));

    }

    public void updateUser(UserModel user) {
        repository.save(user);
    }

    public ResponseData<?> createUser(String login, CompressedUserInfoData data) {
        if (existsUser(login)) {
            return new ResponseData<>(false, "This user already exists!", null);
        }

        ResponseData<CompressedUserInfoModel> compressedUserInfoModelResponseData = compressedUserInfoService.createCompressedUserInfo(data);
        if (!compressedUserInfoModelResponseData.isSuccess()) {
            return new ResponseData<>(false, compressedUserInfoModelResponseData.getDescription(), null);
        }

        ResponseData<UserInfoModel> userInfoModelResponseData = userInfoService.createUserInfo();
        if (!userInfoModelResponseData.isSuccess()) {
            return new ResponseData<>(false, userInfoModelResponseData.getDescription(), null);
        }

        CompressedUserInfoModel compressedUserInfo = compressedUserInfoModelResponseData.getData();
        UserInfoModel userInfo = userInfoModelResponseData.getData();
        UserModel user = new UserModel();
        user.setLogin(login);

        userInfo.setUser(user);
        user.setInfo(userInfo);

        userInfo.setCompressedUserInfo(compressedUserInfo);
        compressedUserInfo.setUserInfo(userInfo);

        repository.save(user);

        return new ResponseData<>(true, "User added successfully!", null);
    }
}
