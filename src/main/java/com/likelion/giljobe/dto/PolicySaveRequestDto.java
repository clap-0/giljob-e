package com.likelion.giljobe.dto;

import com.likelion.giljobe.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PolicySaveRequestDto {

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

    private String residences;    // 참여요건 - 거주지, comma(,)로 구분되어 다수개 제공 가능 (e.g. "SEOUL,BUSAN")

    private String residenceContent;    // 참여요건 - 거주지 및 소득 내용

    private String educations;    // 참여요건 - 학력, comma(,)로 구분되어 다수개 제공 가능

    private String educationContent;    // 참여요건 - 학력 내용

    private String jobStatuses;    // 참여요건 - 취업상태, comma(,)로 구분되어 다수개 제공 가능

    private String jobStatusContent;    // 참여요건 - 취업상태 내용

    private String major;       // 참여요건 - 전공

    private String specialization; // 참여요건 - 특화분야

    private String additionalCondition;     // 참여요건 - 추가단서사항

    private String restrictedParticipant;   // 참여요건 - 참여제한대상


    public Policy toEntity() {
        if (this.ageInfoMin == null) {
            this.ageInfoMin = Integer.MIN_VALUE;
        }
        if (this.ageInfoMax == null) {
            this.ageInfoMax = Integer.MAX_VALUE;
        }

        return Policy.builder()
                .name(this.name)
                .detail(this.detail)
                .bizId(this.bizId)
                .field(this.field)
                .content(this.content)
                .managePeriod(this.managePeriod)
                .applicationPeriod(this.applicationPeriod)
                .scale(this.scale)
                .ageInfoMin(this.ageInfoMin)
                .ageInfoMax(this.ageInfoMax)
                .residenceContent(this.residenceContent)
                .educationContent(this.educationContent)
                .jobStatusContent(this.jobStatusContent)
                .major(this.major)
                .specialization(this.specialization)
                .additionalCondition(this.additionalCondition)
                .restrictedParticipant(this.restrictedParticipant)
                .build();
    }

}
