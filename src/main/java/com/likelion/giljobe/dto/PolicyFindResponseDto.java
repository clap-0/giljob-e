package com.likelion.giljobe.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PolicyFindResponseDto {

    private String name;    // 정책명

    private String detail;  // 정책소개

    private String bizId;   // 정책번호 e.g. R2023070716137

    private String field;   // 정책분야

    private String content; // 지원내용

    private String managePeriod;    // 사업운영기간

    private String applicationPeriod;   // 사업신청기간

    private String scale;   // 지원규모

    private Integer ageInfoMin;     // 참여요건 - 연령 최소치

    private Integer ageInfoMax;     // 참여요건 - 연령 최대치

    private String residence;    // 참여요건 - 거주지 및 소득 내용

    private String education;    // 참여요건 - 학력 내용

    private String major;       // 참여요건 - 전공

    private String jobStatus;    // 참여요건 - 취업상태 내용

    private String specialization; // 참여요건 - 특화분야

    private String additionalCondition;     // 참여요건 - 추가단서사항

    private String restrictedParticipant;   // 참여요건 - 참여제한대상

    private Long views;     // 조회수

    @Override
    public String toString() {
        return "PolicyFindResponseDto{" +
                "name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", bizId='" + bizId + '\'' +
                ", field='" + field + '\'' +
                ", content='" + content + '\'' +
                ", managePeriod='" + managePeriod + '\'' +
                ", applicationPeriod='" + applicationPeriod + '\'' +
                ", scale='" + scale + '\'' +
                ", ageInfoMin=" + ageInfoMin +
                ", ageInfoMax=" + ageInfoMax +
                ", residence='" + residence + '\'' +
                ", education='" + education + '\'' +
                ", major='" + major + '\'' +
                ", jobStatus='" + jobStatus + '\'' +
                ", specialization='" + specialization + '\'' +
                ", additionalCondition='" + additionalCondition + '\'' +
                ", restrictedParticipant='" + restrictedParticipant + '\'' +
                ", views=" + views +
                '}';
    }

    @QueryProjection
    public PolicyFindResponseDto(String name, String detail, String bizId, String field, String content, String managePeriod, String applicationPeriod, String scale, Integer ageInfoMin, Integer ageInfoMax, String residence, String education, String major, String jobStatus, String specialization, String additionalCondition, String restrictedParticipant, Long views) {
        this.name = name;
        this.detail = detail;
        this.bizId = bizId;
        this.field = field;
        this.content = content;
        this.managePeriod = managePeriod;
        this.applicationPeriod = applicationPeriod;
        this.scale = scale;
        this.ageInfoMin = ageInfoMin;
        this.ageInfoMax = ageInfoMax;
        this.residence = residence;
        this.education = education;
        this.major = major;
        this.jobStatus = jobStatus;
        this.specialization = specialization;
        this.additionalCondition = additionalCondition;
        this.restrictedParticipant = restrictedParticipant;
        this.views = views;
    }
}
