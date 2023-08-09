package com.likelion.giljobe.dto;

import com.likelion.giljobe.domain.EducationEnum;
import com.likelion.giljobe.domain.JobStatusEnum;
import com.likelion.giljobe.domain.ResidenceEnum;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
@Schema
public class PolicyListRequestDto {

    @Nullable
    @ApiParam(value = "검색어")
    @ApiModelProperty(example = "창업")
    private String keyword;

    @Nullable
    @ApiParam(value = "연령")
    @ApiModelProperty(example = "22")
    private Integer age;

    @Nullable
    @ApiParam(value = "거주지역")
    @ApiModelProperty(example = "SEOUL")
    private ResidenceEnum residence;

    @Nullable
    @ApiParam(value = "학력")
    @ApiModelProperty(example = "COLLEGE_STUDENT")
    private EducationEnum education;

    @Nullable
    @ApiParam(value = "취업상태")
    @ApiModelProperty(example = "JOB_SEEKER")
    private JobStatusEnum jobStatus;
}
