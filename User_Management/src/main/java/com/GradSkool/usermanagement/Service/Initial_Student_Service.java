package com.GradSkool.usermanagement.Service;

import com.GradSkool.usermanagement.Dto.Initial_Student_Dto;
import com.GradSkool.usermanagement.Entites.Initial_Student;
import com.GradSkool.usermanagement.Vo.InitialStudentVo;
import com.GradSkool.usermanagement.Vo.PageDetails;
import org.springframework.data.domain.Page;

import java.util.List;

public interface Initial_Student_Service {

    Initial_Student addStudent(InitialStudentVo initialStudentVo);

    Initial_Student_Dto getStudent(Integer uid);

  Page<Initial_Student> getAllStudent(PageDetails pageDetails);

    Initial_Student updateStudent(Integer uid,Initial_Student_Dto dto);

    String deleteStudent(Integer uid);

}
