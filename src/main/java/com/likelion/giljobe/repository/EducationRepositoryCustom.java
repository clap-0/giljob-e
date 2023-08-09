package com.likelion.giljobe.repository;

import com.likelion.giljobe.domain.Education;
import com.likelion.giljobe.domain.EducationEnum;
import com.likelion.giljobe.dto.PolicyFindRequestDto;
import com.likelion.giljobe.dto.PolicyFindResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface EducationRepositoryCustom {

    List<Education> findByNames(List<EducationEnum> educationEnums);
}
