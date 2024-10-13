package com.traini8.registry.service;

import com.traini8.registry.dto.request.TrainingCenterRequestDTO;
import com.traini8.registry.dto.response.TrainingCenterResponseDTO;

import java.util.List;

public interface ITrainingCenterService {

    TrainingCenterResponseDTO saveTrainingCenter(TrainingCenterRequestDTO trainingCenterRequestDTO);
    List<TrainingCenterResponseDTO> saveAllTrainingCenters(List<TrainingCenterRequestDTO> trainingCenters);
    List<TrainingCenterResponseDTO> getAllTrainingCenters();
    List<TrainingCenterResponseDTO> filterTrainingCenters(String course, String city, String state);

}
