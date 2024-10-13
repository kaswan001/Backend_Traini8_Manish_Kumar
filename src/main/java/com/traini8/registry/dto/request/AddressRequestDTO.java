package com.traini8.registry.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressRequestDTO {

    @NotBlank(message = "Detailed address is mandatory")
    @Schema(description = "Detailed address of the training center (mandatory)", example = "123 Tech Street")
    private String detailedAddress;

    @NotBlank(message = "City is mandatory")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "City must contain only letters and spaces")
    @Schema(description = "City where the training center is located (mandatory, letters and spaces only)", example = "New York")
    private String city;

    @NotBlank(message = "State is mandatory")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "State must contain only letters and spaces")
    @Schema(description = "State where the training center is located (mandatory, letters and spaces only)", example = "New York")
    private String state;

    @NotBlank(message = "Pincode is mandatory")
    @Schema(description = "Pincode of the training center's location (mandatory)", example = "10001")
    private String pincode;
}
