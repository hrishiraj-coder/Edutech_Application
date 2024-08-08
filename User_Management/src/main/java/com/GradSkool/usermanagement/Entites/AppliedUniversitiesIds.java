package com.GradSkool.usermanagement.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AppliedUniversitiesIds implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer universityId;


    public AppliedUniversitiesIds(Integer universityId) {
        this.universityId = universityId;
    }
}
