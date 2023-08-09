package com.likelion.giljobe.repository;

import com.likelion.giljobe.domain.Education;
import com.likelion.giljobe.domain.EducationEnum;

import java.util.List;


public interface EducationRepositoryCustom {

    List<Education> findByNames(List<EducationEnum> educationEnums);
}
