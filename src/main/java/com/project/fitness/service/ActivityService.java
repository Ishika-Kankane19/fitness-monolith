package com.project.fitness.service;

import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.dto.ActivityResponse;
import com.project.fitness.model.Activity;
import com.project.fitness.model.User;
import com.project.fitness.reprository.ActiviityReprository;
import com.project.fitness.reprository.UserReprository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActiviityReprository activiityReprository;
    private final UserReprository userReprository;
    public @Nullable ActivityResponse trackActivity(ActivityRequest request) {
        User user= userReprository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("Invalid User"+request.getUserId()));
        Activity activity=Activity.builder()
                .user(user)
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrices(request.getAdditionalMetrices()).build();
        Activity savedActivity = activiityReprository.save(activity);
        return mapToResponse(savedActivity);
    }

    private ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse response=new ActivityResponse();
        response.setId(activity.getId());
        response.setUserId(activity.getUser().getId());
        response.setDuration(activity.getDuration());
        response.setType(activity.getType());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setCreatedAt(activity.getCreatedAt());
        activity.setUpdatedAt(activity.getUpdatedAt());
        activity.setAdditionalMetrices(activity.getAdditionalMetrices());
        return response;
    }

    public @Nullable List<ActivityResponse> getUserActivities(String userId) {
        List<Activity> activityList=activiityReprository.findByUserId(userId);
        return activityList.stream().map(this::mapToResponse).collect(Collectors.toList());
    }
}
