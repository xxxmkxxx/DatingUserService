package com.dating.user.data;

import com.dating.user.model.CompressedUserInfoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompressedUserInfoData {
    private String name;
    private String surname;
    private int age;
    private String iconPath;
    private String briefDescription;
    private boolean gender;

    public CompressedUserInfoData(CompressedUserInfoModel model) {
        this.name = model.getName();
        this.surname = model.getSurname();
        this.age = model.getAge();
        this.iconPath = model.getIconPath();
        this.briefDescription = model.getBriefDescription();
        this.gender = model.isGender();
    }
}
