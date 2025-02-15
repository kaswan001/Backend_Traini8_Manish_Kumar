package com.traini8.registry.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TrainingCenterResponseDTO {

    private Long id;
    private String centerName;
    private String centerCode;
    private Integer studentCapacity;
    private List<String> coursesOffered;
    private String contactEmail;
    private String contactPhone;
    private AddressResponseDTO address;
    private Instant createdOn;
}
