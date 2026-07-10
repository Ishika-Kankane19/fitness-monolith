package com.project.fitness.reprository;


import com.project.fitness.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiviityReprository extends JpaRepository<Activity,String> {
    List<Activity> findByUserId(String userId);
}
