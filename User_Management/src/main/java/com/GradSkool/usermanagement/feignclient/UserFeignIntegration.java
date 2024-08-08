package com.GradSkool.usermanagement.feignclient;

import com.GradSkool.usermanagement.Entites.AppliedUniversitiesIds;
import com.GradSkool.usermanagement.Entites.universityintegration.University;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@FeignClient("University-Management")
public interface UserFeignIntegration {

    @GetMapping("/university/getAllUniversities/{ids}")
    List<University> getUniversitiesWithIds(@RequestParam("ids") List<Integer> idsList);

    default List<Integer> convertAppUniIdsToInteger(List<AppliedUniversitiesIds> ids){
        return ids.stream().map(AppliedUniversitiesIds::getUniversityId).collect(Collectors.toList());
    }

}
