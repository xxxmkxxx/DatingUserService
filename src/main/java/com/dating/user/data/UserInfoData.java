package com.dating.user.data;

import com.dating.user.model.UserInfoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoData {
    private CompressedUserInfoData compressedUserInfo;
    private String patronymic;
    private String town;
    private String aboutMyself;

    public UserInfoData(UserInfoModel model) {
        this.compressedUserInfo = new CompressedUserInfoData(model.getCompressedUserInfo());
        this.patronymic = model.getPatronymic();
        this.town = model.getTown();
        this.aboutMyself = model.getAboutMyself();
    }
}
