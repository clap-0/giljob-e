package com.likelion.giljobe.service;

import com.likelion.giljobe.domain.*;
import com.likelion.giljobe.dto.*;
import com.likelion.giljobe.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
        Policy policy = createPolicy(requestDto);

        this.policyRepository.save(policy);
    }

    /**
     * 정책 리스트를 데이터베이스에 저장하는 메서드이다.
     *
     * @param requestDtos - 저장될 정책들에 관한 정보
     */
    @Transactional
    public void saveList(List<PolicySaveRequestDto> requestDtos) {
        for (PolicySaveRequestDto requestDto : requestDtos) {
            Policy policy = createPolicy(requestDto);

            this.policyRepository.save(policy);
        }
    }

    /**
     * PolicySaveRequestDto를 통해 Policy 엔티티를 생성하고, 연관관계를 설정하는 메서드이다.
     *
     * @param requestDto - 저장 요청이 온 정책에 관한 정보
     * @return - 생성된 Policy 엔티티
     */
    private Policy createPolicy(PolicySaveRequestDto requestDto) {
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
        return policy;
    }

    /**
     * 검색어와 필터링 조건을 통해 정책을 조회한다.
     *
     * @param requestDto - 검색어 및 필터링 조건이 포함된 요청값
     * @param pageable - 페이지네이션을 위한 Pageable 인스턴스
     * @return - PolicyListResponseDto 인스턴스를 포함하는 Page 인스턴스
     */
    public Page<PolicyListResponseDto> findByFilter(PolicyListRequestDto requestDto, Pageable pageable) {
        return policyRepository.findByFilter(requestDto, pageable)
                .orElse(new PageImpl<>(new ArrayList<>(), Pageable.ofSize(1), 0));
    }

    /**
     * 정책 ID를 통해 특정 정책의 상세 정보를 조회하는 메서드이다.
     *
     * @param bizId - 조회할 정책의 ID
     * @return - 조회된 정책의 상세 내용
     */
    @Transactional
    public PolicyDetailResponseDto findByBizId(String bizId) {
        Policy policy = policyRepository.findByBizId(bizId)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        // 조회수 증가
        policy.addViews(1);

        return PolicyDetailResponseDto.of(policy);
    }

    /**
     * 정책을 조회수에 대해 내림차순 정렬하여 주어진 개수만큼 반환하는 메서드이다.
     *
     * @param pageSize - 조회할 정책 수
     * @return - 상위 pageSize 개의 정책 정보
     */
    public List<PolicyRankResponseDto> findAllOrderByViewsDesc(int pageSize) {

        // 조회수에 내림차순, 이어서 이름에 오름차순으로 조회
        Sort sort = Sort.by(
                Sort.Order.desc("views"),
                Sort.Order.asc("name")
        );

        Pageable pageable = PageRequest.of(0, pageSize, sort);

        List<Policy> policies = this.policyRepository.findAll(pageable).getContent();

        // Policy를 PolicyRankResponseDto로 변환 후 반환
        return policies.stream()
                .map(PolicyRankResponseDto::of)
                .collect(Collectors.toList());
    }

    /**
     * 주어진 정책번호(bizId)를 가진 정책을 삭제한다.
     *
     * @param bizId - 삭제할 정책번호
     */
    @Transactional
    public void deleteByBizId(String bizId) {
        this.policyRepository.delete(
                this.policyRepository.findByBizId(bizId).orElseThrow(() -> new EmptyResultDataAccessException(1))
        );
    }
}
