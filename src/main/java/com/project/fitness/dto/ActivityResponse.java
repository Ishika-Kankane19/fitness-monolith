package com.project.fitness.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.fitness.model.ActivityType;
import com.project.fitness.model.Recommendations;
import com.project.fitness.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponse {
    private String id;

    private ActivityType type;

    private String userId;
    private Map<String,Object> additionalMetrices;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
