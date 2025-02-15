package com.traini8.registry.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Address {
    private String detailedAddress;
    private String city;
    private String state;
    private String pincode;
}
