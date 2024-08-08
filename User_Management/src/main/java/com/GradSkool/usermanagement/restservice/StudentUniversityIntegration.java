package com.GradSkool.usermanagement.restservice;

import com.GradSkool.usermanagement.Entites.AppliedUniversitiesIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentUniversityIntegration {
    private final String universityServiceUrl = "http://localhost:8082/university";
    private final RestTemplate restTemplate;

    @Autowired
    public StudentUniversityIntegration(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List getListFromUniversity(List<AppliedUniversitiesIds> appliedUniversityIds) {
        try {


            if (appliedUniversityIds != null) {
                List<Integer> uniIds = appliedUniversityIds.stream().map(AppliedUniversitiesIds::getUniversityId).toList();

                String requestUrl = universityServiceUrl + "/getAllUniversities/" +
                        uniIds.stream()
                                .map(Object::toString)  // Convert each Integer to String
                                .collect(Collectors.joining(","));

                return restTemplate.getForObject(requestUrl, List.class);

            } else {
                return Collections.emptyList();

            }


        } catch (HttpClientErrorException e) {
            // Handle 4xx errors (client errors)

            return Collections.emptyList();
        } catch (HttpServerErrorException e) {
            // Handle 5xx errors (server errors)

            return Collections.emptyList();
        } catch (Exception e) {
            // Handle other unexpected errors

            return Collections.emptyList();
        }
    }
}