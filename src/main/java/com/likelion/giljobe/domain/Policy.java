package com.likelion.giljobe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;    // 정책명

    @Column(columnDefinition = "TEXT")
    private String detail;  // 정책소개

    @Column(length = 30)
    private String bizId;   // 정책번호 e.g. R2023070716137

    @Column(length = 10)
    private String field;   // 정책분야

    @Column(columnDefinition = "TEXT")
    private String content; // 지원내용

    private String managePeriod;    // 사업운영기간

    private String applicationPeriod;   // 사업신청기간

    private String scale;   // 지원규모

    private Integer ageInfoMin;     // 참여요건 - 연령 최소치

    private Integer ageInfoMax;     // 참여요건 - 연령 최대치

    @OneToMany
    @JoinColumn(name = "policy_id")
    private List<ResidenceEntity> residence = new ArrayList<>();    // 참여요건 - 거주지

    @Column(columnDefinition = "TEXT")
    private String residenceContent;    // 참여요건 - 거주지 및 소득 내용

    @OneToMany
    @JoinColumn(name = "policy_id")
    private List<EducationEntity> education = new ArrayList<>();    // 참여요건 - 학력

    @Column(columnDefinition = "TEXT")
    private String educationContent;    // 참여요건 - 학력 내용

    @OneToMany
    @JoinColumn(name = "policy_id")
    private List<EmploymentEntity> employment = new ArrayList<>();    // 참여요건 - 취업상태

    @Column(columnDefinition = "TEXT")
    private String employmentContent;    // 참여요건 - 취업상태 내용

    private String major;       // 참여요건 - 전공

    private String specialization; // 참여요건 - 특화분야

    @Column(columnDefinition = "TEXT")
    private String additionalCondition;     // 참여요건 - 추가단서사항

    @Column(columnDefinition = "TEXT")
    private String restrictedParticipant;   // 참여요건 - 참여제한대상

    private Long views;     // 조회수

    private LocalDateTime createdAt;    // 생성일

    private LocalDateTime lastModifiedAt;   // 수정일
}
