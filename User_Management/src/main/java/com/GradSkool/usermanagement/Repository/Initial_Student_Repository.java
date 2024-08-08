package com.GradSkool.usermanagement.Repository;

import com.GradSkool.usermanagement.Entites.Initial_Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Initial_Student_Repository extends JpaRepository<Initial_Student,Integer> {



}
