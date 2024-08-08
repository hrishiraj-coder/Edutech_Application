package com.GradSkool.usermanagement.Service.Impl;

import com.GradSkool.usermanagement.Dto.Initial_Student_Dto;
import com.GradSkool.usermanagement.Entites.Initial_Student;
import com.GradSkool.usermanagement.Entites.universityintegration.University;
import com.GradSkool.usermanagement.Exceptions.NotFoundException;
import com.GradSkool.usermanagement.Mapper.Initial_Student_Mapper;
import com.GradSkool.usermanagement.Repository.Initial_Student_Repository;
import com.GradSkool.usermanagement.Service.Initial_Student_Service;
import com.GradSkool.usermanagement.Vo.InitialStudentVo;
import com.GradSkool.usermanagement.Vo.PageDetails;
import com.GradSkool.usermanagement.feignclient.UserFeignIntegration;
import com.GradSkool.usermanagement.restservice.StudentUniversityIntegration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class Initial_Student_Service_Impl implements Initial_Student_Service {
    private static final Logger LOGGER = LoggerFactory.getLogger(Initial_Student_Service_Impl.class);

    Initial_Student_Repository initialStudentRepository;
    Initial_Student_Mapper initialStudentMapper;

    UserFeignIntegration userFeignIntegration;


    @Autowired
    Initial_Student_Service_Impl(Initial_Student_Repository initialStudentRepository, Initial_Student_Mapper initialStudentMapper, UserFeignIntegration userFeignIntegration) {
        this.initialStudentMapper = initialStudentMapper;
        this.initialStudentRepository = initialStudentRepository;
        this.userFeignIntegration=userFeignIntegration;

    }


    @Override
    @Transactional
    public Initial_Student addStudent(InitialStudentVo initialStudentVo) {

        LOGGER.info("Student has been Added : {}", initialStudentVo);
        Initial_Student initialStudent = initialStudentMapper.voToEntity(initialStudentVo);
        initialStudent.setAppliedUniIdes(initialStudentVo.getAppliedUniIdes());
        return initialStudentRepository.save(initialStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public Initial_Student_Dto getStudent(Integer uid) {

        LOGGER.info("Retrieving Student With UID : {} ", uid);

        Initial_Student_Dto initialStudentDto = initialStudentMapper.
                entityToDto(initialStudentRepository.
                        findById(uid).
                        orElseThrow(() -> {
                            LOGGER.info("Student With UID :{} Not Found", uid);
                            return new NotFoundException();
                        }));


        List<University> appliedUniversities;


/*      RestTemplate Integration

        if (initialStudentDto.getAppliedUniIdes() != null) {
            appliedUniversities = studentUniversityIntegration.
                    getListFromUniversity(initialStudentDto.getAppliedUniIdes());
        } else {
            appliedUniversities=Collections.emptyList();
        }*/

        if(initialStudentDto.getAppliedUniIdes()!=null){
            List<Integer> idsList=userFeignIntegration.convertAppUniIdsToInteger(initialStudentDto.getAppliedUniIdes());
            appliedUniversities=userFeignIntegration.getUniversitiesWithIds(idsList);
        }else {
            appliedUniversities= Collections.emptyList();
        }

        initialStudentDto.setAppliedUniversities(Objects.
                requireNonNullElse(appliedUniversities, Collections.emptyList()));

        return initialStudentDto;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Initial_Student> getAllStudent(PageDetails pageDetails) {

        LOGGER.info("Retrieving All Students Using Pagination");
        Pageable pageable = PageRequest.of(pageDetails.getPageNo(), pageDetails.getPageSize());
        return initialStudentRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Initial_Student updateStudent(Integer uid, Initial_Student_Dto dto) {
        LOGGER.info("Updating Student With UID : {}", uid);
        Initial_Student initialStudent = initialStudentRepository.
                findById(uid).
                orElseThrow(() -> {
                    LOGGER.info("Student With UID {} Not Found For Update", uid);
                    return new NotFoundException();
                });

        initialStudentMapper.updateStudentToDto(dto, initialStudent);

        LOGGER.info("Updated values From Dto To Entity");

        return initialStudentRepository.save(initialStudent);

    }

    @Override
    @Transactional
    public String deleteStudent(Integer uid) {
        LOGGER.info("Deleting Student With UID : {} ", uid);

        if (uid == null) {
            LOGGER.info("Invalid UID Provided For Deletion");
            throw new NotFoundException();
        } else {
            initialStudentRepository.deleteById(uid);
            return "Deleted SuccessFully";
        }


    }
}
