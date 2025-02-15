package com.traini8.registry.service;

import com.traini8.registry.dto.request.AddressRequestDTO;
import com.traini8.registry.dto.request.TrainingCenterRequestDTO;
import com.traini8.registry.dto.response.AddressResponseDTO;
import com.traini8.registry.dto.response.TrainingCenterResponseDTO;
import com.traini8.registry.entity.Address;
import com.traini8.registry.entity.TrainingCenter;
import com.traini8.registry.repository.TrainingCenterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingCenterService implements ITrainingCenterService {

    // Logger for this service class
    private static final Logger logger = LoggerFactory.getLogger(TrainingCenterService.class);

    // Dependency injection of TrainingCenterRepository
    private final TrainingCenterRepository trainingCenterRepository;

    @Transactional
    @Override
    public List<TrainingCenterResponseDTO> saveAllTrainingCenters(List<TrainingCenterRequestDTO> trainingCenters) {
        logger.info("Attempting to save batch of training centers. Count: {}", trainingCenters.size());

        List<TrainingCenter> entities = trainingCenters.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        List<TrainingCenter> savedCenters = trainingCenterRepository.saveAll(entities);
        logger.info("Batch save successful. Saved count: {}", savedCenters.size());

        return savedCenters.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public TrainingCenterResponseDTO saveTrainingCenter(TrainingCenterRequestDTO trainingCenterRequestDTO) {
        logger.info("Attempting to save training center: {}", trainingCenterRequestDTO.getCenterName());

        TrainingCenter trainingCenter = convertToEntity(trainingCenterRequestDTO);
        TrainingCenter savedCenter = trainingCenterRepository.save(trainingCenter);
        logger.info("Save successful for training center: {}", savedCenter.getCenterName());

        return convertToResponseDTO(savedCenter);
    }

    // Retrieve all training centers from the repository and log the retrieval status
    @Override
    public List<TrainingCenterResponseDTO> getAllTrainingCenters() {
        logger.info("Fetching all training centers from repository.");
        List<TrainingCenter> centers = trainingCenterRepository.findAll();
        logger.info("Retrieved {} training centers.", centers.size());

        // Convert entities to response DTOs for returning
        return centers.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Filters training centers based on provided criteria and logs the filtering process
    @Override
    public List<TrainingCenterResponseDTO> filterTrainingCenters(String course, String city, String state) {
        logger.info("Filtering training centers with course: {}, city: {}, state: {}", course, city, state);
        List<TrainingCenter> centers = trainingCenterRepository.filterTrainingCenters(course, city, state);
        logger.info("Retrieved {} training centers after filtering.", centers.size());

        // Convert entities to response DTOs for returning
        return centers.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Converts TrainingCenterRequestDTO to TrainingCenter entity for persistence
    private TrainingCenter convertToEntity(TrainingCenterRequestDTO dto) {
        // Create Address entity from AddressRequestDTO
        AddressRequestDTO addressDTO = dto.getAddress();
        Address address = Address.builder()
                .detailedAddress(addressDTO.getDetailedAddress())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .pincode(addressDTO.getPincode())
                .build();

        // Create TrainingCenter entity from TrainingCenterRequestDTO
        return TrainingCenter.builder()
                .centerName(dto.getCenterName())
                .centerCode(dto.getCenterCode())
                .studentCapacity(dto.getStudentCapacity())
                .coursesOffered(dto.getCoursesOffered())
                .contactEmail(dto.getContactEmail())
                .contactPhone(dto.getContactPhone())
                .address(address)
                .build();
    }

    // Converts TrainingCenter entity to TrainingCenterResponseDTO for returning
    private TrainingCenterResponseDTO convertToResponseDTO(TrainingCenter entity) {
        // Create AddressResponseDTO from Address entity
        Address address = entity.getAddress();
        AddressResponseDTO addressResponseDTO = AddressResponseDTO.builder()
                .detailedAddress(address.getDetailedAddress())
                .city(address.getCity())
                .state(address.getState())
                .pincode(address.getPincode())
                .build();

        // Create TrainingCenterResponseDTO from TrainingCenter entity
        return TrainingCenterResponseDTO.builder()
                .id(entity.getId())
                .centerName(entity.getCenterName())
                .centerCode(entity.getCenterCode())
                .studentCapacity(entity.getStudentCapacity())
                .coursesOffered(entity.getCoursesOffered())
                .contactEmail(entity.getContactEmail())
                .contactPhone(entity.getContactPhone())
                .address(addressResponseDTO)
                .createdOn(entity.getCreatedOn())
                .build();
    }
}
