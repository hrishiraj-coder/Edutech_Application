package com.GradSkool.universitymanagement.controller;

import com.GradSkool.universitymanagement.dto.UniversityDto;
import com.GradSkool.universitymanagement.entites.University;
import com.GradSkool.universitymanagement.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {

    UniversityService universityService;

    @Autowired
    UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @RequestMapping("/getUniversity/{uniId}")
    public ResponseEntity<UniversityDto> getUniversity(@PathVariable Integer uniId) {
        UniversityDto universityDto = universityService.getUniversity(uniId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(universityDto);
    }


    @GetMapping("/getAllUniversities/{uids}")
    public ResponseEntity<List<UniversityDto>> getAllUniversities(@PathVariable List<Integer> uids) {
        List<UniversityDto> universityDtoList = universityService.getAllUniversities(uids);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(universityDtoList);
    }


    @PostMapping("/addUniversity")
    public ResponseEntity<University> addUniversity(@RequestBody UniversityDto universityDto) {

        University university = universityService.addUniversity(universityDto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(university);

    }


    @PutMapping("/updateUniversity/{uniId}")
    public ResponseEntity<University> updateUniversity(@PathVariable Integer uniId, @RequestBody UniversityDto universityDto) {

        University university = universityService.updateUniversity(uniId, universityDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(university);
    }


    @DeleteMapping("/deleteUniversity/{uniId}")
    public ResponseEntity<String> deleteUniversity(@PathVariable Integer uniId) {
        String status1 = universityService.deleteUniversity(uniId);
        return ResponseEntity.ok().body(status1);
    }


}
