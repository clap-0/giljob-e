package com.likelion.giljobe.service;

import com.likelion.giljobe.domain.*;
import com.likelion.giljobe.dto.PolicyFindRequestDto;
import com.likelion.giljobe.dto.PolicyFindResponseDto;
import com.likelion.giljobe.dto.PolicySaveRequestDto;
import com.likelion.giljobe.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final EducationRepository educationRepository;
    private final JobStatusRepository jobStatusRepository;
    private final ResidenceRepository residenceRepository;

    /**
     * 정책을 데이터베이스에 저장한다.
     *
     * @param requestDto 저장될 정책에 관한 정보
     */
    @Transactional
    public void save(PolicySaveRequestDto requestDto) {
        Policy policy = requestDto.toEntity();

        // 참여요건 중 학력 목록을 policy 엔티티에 저장한다.
        String educations = requestDto.getEducations();
        if (educations != null && !educations.isEmpty()) {
            String[] educationArray = educations.split(",");

            List<EducationEnum> educationEnums = Arrays.stream(educationArray)
                    .map(EducationEnum::valueOf)
                    .collect(Collectors.toList());

            Map<EducationEnum, Education> educationMap = educationRepository.findByNames(educationEnums)
                    .stream()
                    .collect(Collectors.toMap(Education::getName, education -> education));

            for (EducationEnum educationEnum : educationEnums) {
                Education education = educationMap.get(educationEnum);
                if (education != null) {
                    PolicyEducation.create(policy, education);
                }
            }
        }

        // 참여요건 중 취업상태 목록을 policy 엔티티에 저장한다.
        String jobStatuses = requestDto.getJobStatuses();
        if (jobStatuses != null && !jobStatuses.isEmpty()) {
            String[] jobStatusArray = jobStatuses.split(",");

            List<JobStatusEnum> jobStatusEnums = Arrays.stream(jobStatusArray)
                    .map(JobStatusEnum::valueOf)
                    .collect(Collectors.toList());

            Map<JobStatusEnum, JobStatus> jobStatusMap = jobStatusRepository.findByNames(jobStatusEnums)
                    .stream()
                    .collect(Collectors.toMap(JobStatus::getName, jobStatus -> jobStatus));

            for (JobStatusEnum jobStatusEnum : jobStatusEnums) {
                JobStatus jobStatus = jobStatusMap.get(jobStatusEnum);
                if (jobStatus != null) {
                    PolicyJobStatus.create(policy, jobStatus);
                }
            }
        }

        // 참여요건 중 거주지 목록을 policy 엔티티에 저장한다.
        String residences = requestDto.getResidences();
        if (residences != null && !residences.isEmpty()) {
            String[] residenceArray = residences.split(",");

            List<ResidenceEnum> residenceEnums = Arrays.stream(residenceArray)
                    .map(ResidenceEnum::valueOf)
                    .collect(Collectors.toList());

            Map<ResidenceEnum, Residence> residenceMap = residenceRepository.findByNames(residenceEnums)
                    .stream()
                    .collect(Collectors.toMap(Residence::getName, residence -> residence));

            for (ResidenceEnum residenceEnum : residenceEnums) {
                Residence residence = residenceMap.get(residenceEnum);
                if (residence != null) {
                    PolicyResidence.create(policy, residence);
                }
            }
        }

        this.policyRepository.save(policy);
    }

    /**
     * 검색어와 필터링 조건을 통해 정책을 조회한다.
     *
     * @param requestDto - 검색어 및 필터링 조건이 포함된 요청값
     * @param pageable - 페이지네이션을 위한 Pageable 인스턴스
     * @return - PolicyFindResponseDto 인스턴스를 포함하는 Page 인스턴스
     */
    public Page<PolicyFindResponseDto> findByFilter(PolicyFindRequestDto requestDto, Pageable pageable) {
        return policyRepository.findByFilter(requestDto, pageable)
                .orElse(new PageImpl<>(new ArrayList<>(), Pageable.ofSize(0), 0));
    }
}
