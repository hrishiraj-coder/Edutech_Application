package com.GradSkool.universitymanagement.mapper;

import com.GradSkool.universitymanagement.dto.UniversityDto;
import com.GradSkool.universitymanagement.entites.University;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UniversityMapper {

    University dtoTOEntity(UniversityDto universityDto);

    UniversityDto entityTODto(University university);

    void updateUniversity(UniversityDto dto,@MappingTarget University entity);

}
