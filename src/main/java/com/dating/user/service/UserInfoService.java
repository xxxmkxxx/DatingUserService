package com.dating.user.service;

import com.dating.user.data.ResponseData;
import com.dating.user.data.UserInfoData;
import com.dating.user.mapper.UserInfoDataMapper;
import com.dating.user.model.UserInfoModel;
import com.dating.user.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserInfoService {
    private UserInfoRepository repository;

    public ResponseData<UserInfoData> getUserInfoByLogin(String login) {
        Optional<UserInfoModel> userInfoOptional = repository.findByUserLogin(login);

        return userInfoOptional
                .map(userInfoModel -> new ResponseData<>(true, null, UserInfoDataMapper.map(userInfoOptional.get())))
                .orElse(new ResponseData<>(false, "No user information found for this login!", null));
    }

    public ResponseData<UserInfoModel> createUserInfo() {
        return new ResponseData<>(true, null, repository.save(new UserInfoModel()));
    }
}
