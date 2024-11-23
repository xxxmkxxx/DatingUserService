package com.dating.user.mapper;

import com.dating.user.data.CompressedUserInfoData;
import com.dating.user.model.CompressedUserInfoModel;

public class CompressedUserInfoDataMapper {
    public static CompressedUserInfoData map(CompressedUserInfoModel model) {
        return CompressedUserInfoData.builder()
                .name(model.getName())
                .surname(model.getSurname())
                .age(model.getAge())
                .briefDescription(model.getBriefDescription())
                .iconPath(model.getIconPath())
                .build();
    }
}
