package com.likelion.giljobe.repository;

import com.likelion.giljobe.domain.JobStatus;
import com.likelion.giljobe.domain.JobStatusEnum;

import java.util.List;


public interface JobStatusRepositoryCustom {

    List<JobStatus> findByNames(List<JobStatusEnum> jobStatusEnums);
}
