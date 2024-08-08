package com.GradSkool.usermanagement.Dto;

import com.GradSkool.usermanagement.Entites.AppliedUniversitiesIds;
import com.GradSkool.usermanagement.Entites.universityintegration.University;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class Initial_Student_Dto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    @JsonProperty("appliedUniIdes")
    private List<AppliedUniversitiesIds> appliedUniIdes;
    private List<University> appliedUniversities;


    public Initial_Student_Dto(String john, String doe, String number, String mail, String newPassword) {
    }
}
