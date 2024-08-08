package com.GradSkool.usermanagement.Mapper;

import com.GradSkool.usermanagement.Dto.Initial_Student_Dto;
import com.GradSkool.usermanagement.Entites.Initial_Student;
import com.GradSkool.usermanagement.Vo.InitialStudentVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface Initial_Student_Mapper {
    Initial_Student dtoToEntity(Initial_Student_Dto dto);

    Initial_Student_Dto entityToDto(Initial_Student entity);

    Initial_Student voToEntity(InitialStudentVo vo);

    void updateStudentToDto(Initial_Student_Dto dto, @MappingTarget Initial_Student entity);
}
