package com.GradSkool.usermanagement.Controller.circuitbreakers;

import com.GradSkool.usermanagement.Entites.Initial_Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;


public class ISCCircuitBreaker {

    public ResponseEntity<Initial_Student> getSingleStudentFallBack(Integer uid,Exception e) {

        Initial_Student initialStudent = Initial_Student.of().uid(uid).firstName("empty").
                lastName("empty").email("empty").
                phoneNumber("empty").
                appliedUniIdes(Collections.emptyList()).build();
        return ResponseEntity.ok(initialStudent);
    }

}
