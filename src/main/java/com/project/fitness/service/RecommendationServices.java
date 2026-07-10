package com.project.fitness.service;

import com.project.fitness.dto.RecommendationRequest;
import com.project.fitness.model.Activity;
import com.project.fitness.model.Recommendations;
import com.project.fitness.model.User;
import com.project.fitness.reprository.ActiviityReprository;
import com.project.fitness.reprository.RecommendationReprository;
import com.project.fitness.reprository.UserReprository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationServices {
    private final UserReprository userReprository;
    private final ActiviityReprository activiityReprository;
    private final RecommendationReprository recommendationReprository;

    public Recommendations generateRecommendation(RecommendationRequest request) {
        User user = userReprository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not Found:" + request.getUserId()));
        Activity activity = activiityReprository.findById(request.getActivityId()).orElseThrow(() -> new RuntimeException("Activity not Found:" + request.getActivityId()));

        Recommendations recommendations = Recommendations.builder()
                .user(user)
                .activity(activity)
                .improvements(request.getImprovements())
                .safety(request.getSafety())
                .suggestions(request.getSuggestions()).build();

        return recommendationReprository.save(recommendations);
    }

    public List<Recommendations> getUserRecommendation(String userId) {
        return recommendationReprository.findByUserId(userId);
    }

//    public List<Recommendations> getActivityRecommendation(String activityId) {
//    }

    public List<Recommendations> getActivityRecommendation(String activityId) {
        return recommendationReprository.findByActivityId(activityId);
    }
}
