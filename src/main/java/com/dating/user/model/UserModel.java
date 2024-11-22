package com.dating.user.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_login", nullable = false)
    private String userLogin;

    @OneToOne(mappedBy = "user")
    private UserInfoModel userInfo;
}