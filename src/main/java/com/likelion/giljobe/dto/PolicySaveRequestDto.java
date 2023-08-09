package com.likelion.giljobe.dto;

import com.likelion.giljobe.domain.*;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class PolicySaveRequestDto {
    @ApiParam(value = "정책명")
    @Schema(description = "정책명", example = "사회적경제 문화예술 청년창업지원 프로젝트")
    private String name;

    @ApiParam(value = "정책소개")
    @Schema(description = "정책소개", example = "문화예술분야 사회적경제 기업 창업아이템을 보유한 청년 창업가 지원")
    private String detail;

    @ApiParam(value = "정책번호")
    @Schema(description = "정책번호", example = "R2023050912273")
    private String bizId;

    @ApiParam(value = "정책분야")
    @Schema(description = "정책분야", example = "023010")
    private String field;

    @ApiParam(value = "지원내용")
    @Schema(description = "지원내용", example = "1. 초기 창업을 위한 사업비 지원 사업 : 12팀(명) 내외 - 사업비(최소 20백만원 ~ 최대 30백만원), 창업 역량강화 아카데미 지원 등\n" +
            "2. 사업 안정화를 위한 임차료 지원 사업 : 8팀(명) 내외 - 임차료(최대 5.4백만원/월 60만원 한도), 창업 역량강화 아카데미 지원 등")
    private String content;

    @ApiParam(value = "사업운영기간")
    @Schema(description = "사업운영기간", example = "2023.01.01.~2023.12.31.")
    private String managePeriod;

    @ApiParam(value = "사업신청기간")
    @Schema(description = "사업신청기간", example = "2023.01.30.~2022.02.24.")
    private String applicationPeriod;

    @ApiParam(value = "지원규모")
    @Schema(description = "지원규모", example = "총 20명(팀) 내외")
    private String scale;

    @ApiParam(value = "참여요건 - 연령 최소치")
    @Schema(description = "참여요건 - 연령 최소치", example = "19")
    private Integer ageInfoMin;

    @ApiParam(value = "참여요건 - 연령 최대치")
    @Schema(description = "참여요건 - 연령 최대치", example = "39")
    private Integer ageInfoMax;

    @ApiParam(value = "참여요건 - 거주지\n" +
            "아래 목록 중 comma(,)로 구분하여 다수개 제공 가능 (e.g. SEOUL,BUSAN)\n" +
            "ALL, SEOUL, BUSAN, DAEGU, INCHEON, GWANGJU, DAEJEON, ULSAN,\n" +
            "GYOUNGGI, GANGWON, CHUNGBUK, CHUNGNAM, JEONBUK, JEONNAM, GYEONGBUK, GYEONGNAM,\n" +
            "JEJU, SEJONG")
    @Schema(description = "참여요건 - 거주지\n" +
            "아래 목록 중 comma(,)로 구분하여 다수개 제공 가능\n" +
            "ALL, SEOUL, BUSAN, DAEGU, INCHEON, GWANGJU, DAEJEON, ULSAN,\n" +
            "GYOUNGGI, GANGWON, CHUNGBUK, CHUNGNAM, JEONBUK, JEONNAM, GYEONGBUK, GYEONGNAM,\n" +
            "JEJU, SEJONG", example = "SEOUL,BUSAN")
    private String residences;

    @ApiParam(value = "참여요건 - 거주지 및 소득 내용")
    @Schema(description = "참여요건 - 거주지 및 소득 내용", example = "서초구 거주 또는 활동하는 만 19세 이상 39세 이하 청년 중 문화/예술분야의 사회적경제기업 진입을 희망하는 예비/신규 창업자(팀)")
    private String residenceContent;

    @ApiParam(value = "참여요건 - 학력\n" +
            "아래 목록 중 comma(,)로 구분하여 다수개 제공 가능 (e.g. COLLEGE_STUDENT)\n" +
            "UNDER_HIGH_SCHOOL, HIGH_SCHOOL_GRADUATE, COLLEGE_STUDENT, " +
            "COLLEGE_GRADUATE, DOCTORATE, ALL")
    @Schema(description = "참여요건 - 학력\n" +
            "아래 목록 중 comma(,)로 구분하여 다수개 제공 가능\n" +
            "UNDER_HIGH_SCHOOL, HIGH_SCHOOL_GRADUATE, COLLEGE_STUDENT, " +
            "COLLEGE_GRADUATE, DOCTORATE, ALL", example = "COLLEGE_STUDENT")
    private String educations;

    @ApiParam(value = "참여요건 - 학력 내용")
    @Schema(description = "참여요건 - 학력 내용", example = "제한없음")
    private String educationContent;

    @ApiParam(value = "참여요건 - 취업상태\n" +
            "아래 목록 중 comma(,)로 구분하여 다수개 제공 가능 (e.g. EMPLOYEE,FREELANCER)\n" +
            "ALL, JOB_SEEKER, ENTREPRENEUR, EMPLOYEE, SELF_EMPLOYED, FREELANCER, TEMPORARY_WORKER")
    @Schema(description = "참여요건 - 취업상태\n" +
            "아래 목록 중 comma(,)로 구분하여 다수개 제공 가능\n" +
            "ALL, JOB_SEEKER, ENTREPRENEUR, EMPLOYEE, SELF_EMPLOYED, FREELANCER, TEMPORARY_WORKER", example = "EMPLOYEE,FREELANCER")
    private String jobStatuses;    // 참여요건 - 취업상태, comma(,)로 구분되어 다수개 제공 가능

    @ApiParam(value = "참여요건 - 취업상태 내용")
    @Schema(description = "참여요건 - 취업상태 내용", example = "제한없음")
    private String jobStatusContent;    // 참여요건 - 취업상태 내용


    @ApiParam(value = "참여요건 - 전공")
    @Schema(description = "참여요건 - 전공", example = "제한없음")
    private String major;       // 참여요건 - 전공

    @ApiParam(value = "참여요건 - 특화분야")
    @Schema(description = "참여요건 - 특화분야", example = "제한없음")
    private String specialization; // 참여요건 - 특화분야

    @ApiParam(value = "참여요건 - 추가단서사항")
    @Schema(description = "참여요건 - 추가단서사항", example = "초기 창업자는 사업개시일 3년 미만인 자를 의미하며, 임차료 지원사업의 경우 서초구 내 사무실에 한정함")
    private String additionalCondition;     // 참여요건 - 추가단서사항

    @ApiParam(value = "참여요건 - 참여제한대상")
    @Schema(description = "참여요건 - 참여제한대상", example = "정부지원사업에 참여제한으로 제재중인자 또는 기업")
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
