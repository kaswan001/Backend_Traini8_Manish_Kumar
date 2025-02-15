package com.traini8.registry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "training_center")
public class TrainingCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String centerName;
    private String centerCode;
    private Integer studentCapacity;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> coursesOffered;

    private String contactEmail;
    private String contactPhone;

    @Embedded
    private Address address;

    private Instant createdOn;

    @PrePersist
    public void prePersist() {
        createdOn = Instant.now(); // Sets the timestamp before saving to DB
    }
}

