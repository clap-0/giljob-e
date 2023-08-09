package com.likelion.giljobe.service;

import com.likelion.giljobe.domain.JobStatus;
import com.likelion.giljobe.domain.JobStatusEnum;
import com.likelion.giljobe.repository.JobStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobStatusService {
    
    private final JobStatusRepository jobStatusRepository;

    /**
     * JobStatus 엔티티를 저장하는 메서드
     * 
     * @param jobStatusEnum - JobStatus 엔티티가 가지는 JobStatusEnum 인스턴스 값
     */
    public void save(JobStatusEnum jobStatusEnum) {
        JobStatus jobStatus = JobStatus.of(jobStatusEnum);
        
        this.jobStatusRepository.save(jobStatus);
    }
}
