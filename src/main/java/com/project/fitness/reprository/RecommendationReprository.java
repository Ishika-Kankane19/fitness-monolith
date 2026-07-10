package com.project.fitness.reprository;

import com.project.fitness.model.Activity;
import com.project.fitness.model.Recommendations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecommendationReprository extends JpaRepository<Recommendations,String> {
        List<Recommendations> findByUserId(String userId);

        List<Recommendations> findByActivityId(String activityId);
}
