package com.GradSkool.usermanagement.Entites;

import com.GradSkool.usermanagement.Entites.universityintegration.University;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder(builderMethodName = "of")
@NoArgsConstructor
@AllArgsConstructor
public class Initial_Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer uid;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private String password;
        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "initial_student_id")
        private List<AppliedUniversitiesIds> appliedUniIdes;




}
