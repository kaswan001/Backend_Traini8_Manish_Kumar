package com.traini8.registry.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.ElementCollection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TrainingCenterRequestDTO {

    @NotBlank(message = "Center name is mandatory")
    @Length(max = 40, message = "Center name should be less than 40 characters")
    @Schema(description = "The name of the training center (mandatory, max 40 characters)", example = "Tech Academy")
    private String centerName;

    @NotBlank(message = "Center code is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9]{12}$", message = "Center code must be exactly 12 alphanumeric characters")
    @Schema(description = "A unique code for the center, must be exactly 12 alphanumeric characters", example = "ABC123456789")
    private String centerCode;

    @NotNull(message = "Student capacity cannot be null")
    @Min(value = 1, message = "Student capacity must be at least 1")
    @Schema(description = "The maximum number of students the center can accommodate (minimum 1)", example = "100")
    private Integer studentCapacity;

    @ElementCollection
    @Schema(description = "List of courses offered by the training center")
    private List<String> coursesOffered;

    @Email(message = "Invalid email format")
    @Schema(description = "Contact email for the training center", example = "info@techacademy.com")
    private String contactEmail;

    @NotBlank(message = "Contact phone is mandatory")
    @Pattern(regexp = "^\\+?[1-9]\\d{0,14}([-.\\s()]?\\d{1,4}){1,4}$\n", message = "Invalid phone number format")
    @Schema(description = "Contact phone number (mandatory, must be numeric)", example = "+1234567890, +12 345 678 90, +1 (234) 567-8901, +44-20-7946-0958")
    private String contactPhone;

    @Valid
    private AddressRequestDTO address;
}
