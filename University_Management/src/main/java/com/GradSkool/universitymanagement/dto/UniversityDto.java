package com.GradSkool.universitymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDto {
    private Integer universityID;
    private String name;
    private String location;
    private Integer establishmentYear;
    private String type;
}
