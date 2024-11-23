package com.dating.user.mapper;

import com.dating.user.data.UserInfoData;
import com.dating.user.model.UserInfoModel;

public class UserInfoDataMapper {
    public static UserInfoData map(UserInfoModel model) {
        return UserInfoData.builder()
                .compressedUserInfo(CompressedUserInfoDataMapper.map(model.getCompressedUserInfo()))
                .patronymic(model.getPatronymic())
                .town(model.getTown())
                .aboutMyself(model.getAboutMyself())
                .build();
    }
}
