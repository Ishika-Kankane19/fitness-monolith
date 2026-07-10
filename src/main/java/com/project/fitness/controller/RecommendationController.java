package com.project.fitness.controller;

import com.project.fitness.dto.RecommendationRequest;
import com.project.fitness.model.Recommendations;
import com.project.fitness.service.RecommendationServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationServices recommendationServices;

    @PostMapping("/generate")
    public ResponseEntity<Recommendations> generateRecommendation(@RequestBody RecommendationRequest request){
        Recommendations recommendations= recommendationServices.generateRecommendation(request);
        return ResponseEntity.ok(recommendations);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendations>> getUserRecommendation(@PathVariable String userId){
        List<Recommendations> recommendationsList = recommendationServices.getUserRecommendation(userId);
        return ResponseEntity.ok(recommendationsList);
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<Recommendations>> getActivityRecommendation(@PathVariable String activityId){
        List<Recommendations> recommendationsList = recommendationServices.getActivityRecommendation(activityId);
        return ResponseEntity.ok(recommendationsList);
    }
}
