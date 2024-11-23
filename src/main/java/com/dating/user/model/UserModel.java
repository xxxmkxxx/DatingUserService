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
    private int id;

    @Column(name = "login", nullable = false)
    private String login;

    @OneToOne(mappedBy = "user")
    private UserInfoModel info;
}
