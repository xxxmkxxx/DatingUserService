package com.dating.userinfo.data;

import com.dating.userinfo.model.CompressedUserInfoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompressedUserInfoData {
    private String firstName;
    private int age;
    private String iconPath;
    private String briefDescription;
    private boolean gender;

    public CompressedUserInfoData(CompressedUserInfoModel model) {
        this.firstName = model.getFirstName();
        this.age = model.getAge();
        this.iconPath = model.getIconPath();
        this.briefDescription = model.getBriefDescription();
        this.gender = model.isGender();
    }
}
