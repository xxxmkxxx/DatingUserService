package com.dating.userinfo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "bad_habit")
public class BadHabitModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bad_habit_id")
    private int badHabitId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "badHabits", fetch = FetchType.LAZY)
    private Set<UserInfoModel> userInfos;
}
