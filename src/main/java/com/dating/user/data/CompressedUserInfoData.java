package com.dating.user.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompressedUserInfoData {
    private String name;
    private String surname;
    private int age;
    private String iconPath;
    private String briefDescription;
    private boolean gender;
}
