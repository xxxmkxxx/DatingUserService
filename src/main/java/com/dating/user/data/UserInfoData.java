package com.dating.user.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoData {
    private CompressedUserInfoData compressedUserInfo;
    private String patronymic;
    private String town;
    private String aboutMyself;
}
