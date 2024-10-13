package com.traini8.registry.controller;

import com.traini8.registry.dto.request.TrainingCenterRequestDTO;
import com.traini8.registry.dto.response.TrainingCenterResponseDTO;
import com.traini8.registry.exception.EmptyRequestBodyException;
import com.traini8.registry.service.ITrainingCenterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/training-centers")
@Validated
@Tag(name = "Training Centers", description = "Operations related to training centers")
public class TrainingCenterController {

    private final ITrainingCenterService trainingCenterService;
    private static final Logger logger = LoggerFactory.getLogger(TrainingCenterController.class);

    @Operation(summary = "Save batch of training centers",
            description = "Saves a list of training centers and returns the saved entities.")
    @PostMapping("/save-batch")
    public ResponseEntity<List<TrainingCenterResponseDTO>> createTrainingCenters(
            @Valid @RequestBody List<TrainingCenterRequestDTO> trainingCenters) {

        if (trainingCenters.isEmpty()) {
            throw new EmptyRequestBodyException("Request body is required and cannot be empty for batch saving.");
        }

        logger.info("Received request to save batch of training centers. Count: {}", trainingCenters.size());
        List<TrainingCenterResponseDTO> savedCenters = trainingCenterService.saveAllTrainingCenters(trainingCenters);
        logger.info("Successfully saved batch of training centers. Saved count: {}", savedCenters.size());

        return new ResponseEntity<>(savedCenters, HttpStatus.CREATED);
    }

    @Operation(summary = "Save a single training center",
            description = "Saves a single training center and returns the saved entity.")
    @PostMapping("/save")
    public ResponseEntity<TrainingCenterResponseDTO> createTrainingCenter(
            @Valid @RequestBody TrainingCenterRequestDTO trainingCenterRequestDTO) {

        logger.info("Received request to save a training center: {}", trainingCenterRequestDTO.getCenterName());
        TrainingCenterResponseDTO savedCenter = trainingCenterService.saveTrainingCenter(trainingCenterRequestDTO);
        logger.info("Successfully saved training center: {}", savedCenter.getCenterName());

        return new ResponseEntity<>(savedCenter, HttpStatus.CREATED);
    }

    // Retrieves all training centers
    @Operation(summary = "Retrieve all training centers",
            description = "Returns a list of all training centers.")
    @GetMapping("/getAll")
    public ResponseEntity<List<TrainingCenterResponseDTO>> getAllTrainingCenters() {
        logger.info("Received request to get all training centers.");
        List<TrainingCenterResponseDTO> centers = trainingCenterService.getAllTrainingCenters();
        logger.info("Returning {} training centers.", centers.size());
        return ResponseEntity.ok(centers);
    }

    // Filters training centers based on provided criteria
    @Operation(summary = "Filter training centers",
            description = "Filters training centers based on course, city, and state.")
    @GetMapping("/filter")
    public ResponseEntity<List<TrainingCenterResponseDTO>> filterTrainingCenters(
            @RequestParam(required = false) String course,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state) {

        logger.info("Received request to filter training centers with parameters - course: {}, city: {}, state: {}", course, city, state);
        List<TrainingCenterResponseDTO> centers = trainingCenterService.filterTrainingCenters(course, city, state);
        logger.info("Returning {} filtered training centers.", centers.size());
        return ResponseEntity.ok(centers);
    }
}
