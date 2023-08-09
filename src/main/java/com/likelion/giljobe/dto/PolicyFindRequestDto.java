package com.likelion.giljobe.dto;

import com.likelion.giljobe.domain.EducationEnum;
import com.likelion.giljobe.domain.JobStatusEnum;
import com.likelion.giljobe.domain.ResidenceEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PolicyFindRequestDto {

    private String keyword;

    private Integer age;

    private ResidenceEnum residence;

    private EducationEnum education;

    private JobStatusEnum jobStatus;
}
