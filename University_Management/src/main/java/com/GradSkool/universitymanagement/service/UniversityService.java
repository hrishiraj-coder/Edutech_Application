package com.GradSkool.universitymanagement.service;

import com.GradSkool.universitymanagement.dto.UniversityDto;
import com.GradSkool.universitymanagement.entites.University;

import java.util.List;

public interface UniversityService {


     UniversityDto getUniversity(Integer uniId);

     List<UniversityDto> getAllUniversities(List<Integer> uids);

     University addUniversity(UniversityDto universityDto);

     University updateUniversity(Integer uniId,UniversityDto universityDto);

     String deleteUniversity(Integer uniId);

}
