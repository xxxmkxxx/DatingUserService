package com.dating.userinfo.service;

import com.dating.userinfo.model.BadHabitModel;
import com.dating.userinfo.repository.BadHabitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BadHabitService {
    private BadHabitRepository badHabitRepository;

    public boolean isBadHabitExists(String name) {
        return badHabitRepository.existsByName(name);
    }

    public List<BadHabitModel> getAllBadHabits() {
        return badHabitRepository.findAll();
    }
}
