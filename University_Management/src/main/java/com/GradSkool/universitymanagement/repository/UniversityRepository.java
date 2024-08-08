package com.GradSkool.universitymanagement.repository;

import com.GradSkool.universitymanagement.entites.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University,Integer> {
}
