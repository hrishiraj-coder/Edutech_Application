package com.GradSkool.usermanagement.Vo;

import com.GradSkool.usermanagement.Entites.AppliedUniversitiesIds;
import com.GradSkool.usermanagement.Entites.universityintegration.University;
import lombok.Data;

import java.util.List;
@Data
public class InitialStudentVo {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private List<AppliedUniversitiesIds> appliedUniIdes;
}
