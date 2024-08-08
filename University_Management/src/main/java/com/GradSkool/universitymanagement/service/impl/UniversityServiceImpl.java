package com.GradSkool.universitymanagement.service.impl;

import com.GradSkool.universitymanagement.dto.UniversityDto;
import com.GradSkool.universitymanagement.entites.University;
import com.GradSkool.universitymanagement.exceptions.NotFoundException;
import com.GradSkool.universitymanagement.mapper.UniversityMapper;
import com.GradSkool.universitymanagement.repository.UniversityRepository;
import com.GradSkool.universitymanagement.service.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UniversityServiceImpl.class);

    UniversityRepository universityRepository;
    UniversityMapper universityMapper;

    @Autowired
    UniversityServiceImpl(UniversityMapper universityMapper, UniversityRepository universityRepository) {
        this.universityMapper = universityMapper;
        this.universityRepository = universityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UniversityDto getUniversity(Integer uniId) {
        LOGGER.info("getUniversity called with uniId : {} ", uniId);

        return universityMapper.entityTODto(universityRepository.findById(uniId).orElseThrow(() -> {
                    LOGGER.info("University Not Found : {} ", uniId);
                    return new NotFoundException("University Not Found");
                }
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UniversityDto> getAllUniversities(List<Integer> uids) {

        LOGGER.info("Getting All Universities");

        List<University> universities=universityRepository.findAllById(uids);

        return universities.stream().
                map((university)->universityMapper.entityTODto(university)).toList();
    }

    @Override
    @Transactional
    public University addUniversity(UniversityDto universityDto) {
        LOGGER.info("addUniversity called");
        return universityRepository.save(universityMapper.dtoTOEntity(universityDto));
    }

    @Override
    @Transactional
    public University updateUniversity(Integer uniId, UniversityDto universityDto) {
        LOGGER.info("Updating University with uniId: {} ",uniId);

        University university = universityRepository.findById(uniId).orElseThrow(() -> {
            LOGGER.info("University Not Found : {} ", uniId);
            return new NotFoundException("University Not Found");
        });

        universityMapper.updateUniversity(universityDto, university);

        LOGGER.info("University Updated with uniId: {} ",uniId);

        return university;
    }

    @Override
    @Transactional
    public String deleteUniversity(Integer uniId) {
        LOGGER.info("Deleting University with uniId: {} ",uniId);
         try {
             universityRepository.deleteById(uniId);
             LOGGER.info("Deleted University with uniId: {} ",uniId);
             return "Deleted SuccessFully";
         }catch (Exception e){
             LOGGER.info("Cannot find University with uniId: {} (Deletion Failed)",uniId);
             throw new NotFoundException("Cannot find id with id: "+uniId);
         }




    }
}
