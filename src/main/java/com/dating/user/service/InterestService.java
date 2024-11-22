package com.dating.user.service;

import com.dating.user.model.InterestModel;
import com.dating.user.repository.InterestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InterestService {
    private InterestRepository interestRepository;

    public boolean isInterestExists(String name) {
        return interestRepository.existsByName(name);
    }

    public List<InterestModel> getAllInterests() {
        return interestRepository.findAll();
    }
}
