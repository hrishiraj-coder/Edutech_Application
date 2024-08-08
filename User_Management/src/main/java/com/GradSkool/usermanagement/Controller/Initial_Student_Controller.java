package com.GradSkool.usermanagement.Controller;

import com.GradSkool.usermanagement.Dto.Initial_Student_Dto;
import com.GradSkool.usermanagement.Entites.Initial_Student;
import com.GradSkool.usermanagement.Service.Initial_Student_Service;
import com.GradSkool.usermanagement.Vo.InitialStudentVo;
import com.GradSkool.usermanagement.Vo.PageDetails;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/initial_student_controller")
public class Initial_Student_Controller {

    Initial_Student_Service initialStudentService;
    private final Logger LOGGER = LoggerFactory.getLogger(Initial_Student.class);


    @Autowired
    Initial_Student_Controller(Initial_Student_Service initialStudentService) {
        this.initialStudentService = initialStudentService;
    }

    int retryCount = 1;

    @GetMapping("/getStudent/{uid}")
    @CircuitBreaker(name = "getSingleStudentCB", fallbackMethod = "getSingleStudentFallBack")
    @Retry(name = "getSingleStudentRetry", fallbackMethod = "getSingleStudentFallBack")
    @RateLimiter(name = "getSingleStudentDenied", fallbackMethod = "getSingleStudentDenied")
    public ResponseEntity<Initial_Student_Dto> getStudent(@PathVariable("uid") Integer uid) {
        LOGGER.info("Retry Count {}", retryCount);
        retryCount++;
        Initial_Student_Dto initialStudentDto = initialStudentService.getStudent(uid);
        return ResponseEntity.status(HttpStatus.FOUND).body(initialStudentDto);
    }

    public ResponseEntity<Initial_Student> getSingleStudentFallBack(Integer uid, Exception e) {

        Initial_Student initialStudent = Initial_Student.of().uid(uid).firstName("empty").
                lastName("empty").email("empty").
                phoneNumber("empty").
                appliedUniIdes(Collections.emptyList()).build();
        return ResponseEntity.ok(initialStudent);
    }

    public ResponseEntity<Initial_Student> getSingleStudentDenied(Integer uid, Exception e) {

        Initial_Student initialStudent = Initial_Student.of().uid(1).firstName("Denied").
                lastName("Denied").email("Denied").
                phoneNumber("Denied").
                appliedUniIdes(Collections.emptyList()).build();
        return ResponseEntity.ok(initialStudent);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Initial_Student> addStudent(@RequestBody InitialStudentVo initialStudentVo) {

        Initial_Student initialStudent = initialStudentService.addStudent(initialStudentVo);
        return ResponseEntity.status(HttpStatus.CREATED).body(initialStudent);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<Page<Initial_Student>> getAllStudentPage(@RequestBody PageDetails pageDetails) {
        Page<Initial_Student> page = initialStudentService.getAllStudent(pageDetails);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/updateStudent/{uid}")
    public ResponseEntity<Initial_Student> updateStudent(@PathVariable Integer uid, @RequestBody Initial_Student_Dto dto) {
        Initial_Student initialStudent = initialStudentService.updateStudent(uid, dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(initialStudent);
    }

    @DeleteMapping("/deleteStudent/{uid}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer uid) {
        String status = initialStudentService.deleteStudent(uid);
        return ResponseEntity.ok(status);
    }

}
