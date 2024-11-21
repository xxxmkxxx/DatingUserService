package com.dating.userinfo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "compressed_user_info")
public class CompressedUserInfoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compressed_user_info_id")
    private int compressedUserInfoId;

    @Column(name = "first_name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "profile_icon_path")
    private String iconPath;

    @Column(name = "brief_description", nullable = false)
    private String briefDescription;

    @Column(name = "gender", nullable = false)
    private boolean gender;

    @OneToOne
    @JoinColumn(name = "user_info_id")
    private UserInfoModel userInfo;
}
