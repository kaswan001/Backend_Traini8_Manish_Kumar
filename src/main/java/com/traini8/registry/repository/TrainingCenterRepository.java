package com.traini8.registry.repository;

import com.traini8.registry.entity.TrainingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingCenterRepository extends JpaRepository<TrainingCenter, Long> {

    @Query("SELECT tc FROM TrainingCenter tc WHERE " +
            "(:course IS NULL OR :course = '' OR :course MEMBER OF tc.coursesOffered) AND " +
            "(:city IS NULL OR :city = '' OR tc.address.city = :city) AND " +
            "(:state IS NULL OR :state = '' OR tc.address.state = :state)")
    List<TrainingCenter> filterTrainingCenters(
            @Param("course") String course,
            @Param("city") String city,
            @Param("state") String state);

}
