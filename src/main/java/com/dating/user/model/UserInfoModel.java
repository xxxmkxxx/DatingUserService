package com.dating.user.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "user_info")
public class UserInfoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_info_id")
    private int userInfoId;

    @OneToOne(mappedBy = "userInfo")
    private CompressedUserInfoModel compressedUserInfo;

    @Column(name = "patronomic")
    private String patronymic;

    @Column(name = "town")
    private String town;

    @Column(name = "about_myself")
    private String aboutMyself;

    @ManyToMany
    @JoinTable(
            name = "user_info_interest",
            joinColumns = @JoinColumn(name = "user_info_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id")
    )
    private Set<InterestModel> interests;


    @ManyToMany
    @JoinTable(
            name = "user_info_bad_habit",
            joinColumns = @JoinColumn(name = "user_info_id"),
            inverseJoinColumns = @JoinColumn(name = "bad_habit_id")
    )
    private Set<BadHabitModel> badHabits;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
