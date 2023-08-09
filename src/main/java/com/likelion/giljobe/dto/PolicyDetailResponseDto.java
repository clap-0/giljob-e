package com.likelion.giljobe.dto;

import com.likelion.giljobe.domain.Policy;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PolicyDetailResponseDto {


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

    @ApiParam(value = "참여요건 - 거주지 및 소득 내용")
    @Schema(description = "참여요건 - 거주지 및 소득 내용", example = "서초구 거주 또는 활동하는 만 19세 이상 39세 이하 청년 중 문화/예술분야의 사회적경제기업 진입을 희망하는 예비/신규 창업자(팀)")
    private String residence;

    @ApiParam(value = "참여요건 - 학력 내용")
    @Schema(description = "참여요건 - 학력 내용", example = "제한없음")
    private String education;

    @ApiParam(value = "참여요건 - 전공")
    @Schema(description = "참여요건 - 전공", example = "제한없음")
    private String major;

    @ApiParam(value = "참여요건 - 취업상태 내용")
    @Schema(description = "참여요건 - 취업상태 내용", example = "제한없음")
    private String jobStatus;

    @ApiParam(value = "참여요건 - 특화분야")
    @Schema(description = "참여요건 - 특화분야", example = "제한없음")
    private String specialization;

    @ApiParam(value = "참여요건 - 추가단서사항")
    @Schema(description = "참여요건 - 추가단서사항", example = "초기 창업자는 사업개시일 3년 미만인 자를 의미하며, 임차료 지원사업의 경우 서초구 내 사무실에 한정함")
    private String additionalCondition;

    @ApiParam(value = "참여요건 - 참여제한대상")
    @Schema(description = "참여요건 - 참여제한대상", example = "정부지원사업에 참여제한으로 제재중인자 또는 기업")
    private String restrictedParticipant;

    @ApiParam(value = "조회수")
    @Schema(description = "조회수", example = "1")
    private Long views;

    @QueryProjection
    public PolicyDetailResponseDto(String name, String detail, String bizId, String field, String content, String managePeriod, String applicationPeriod, String scale, Integer ageInfoMin, Integer ageInfoMax, String residence, String education, String major, String jobStatus, String specialization, String additionalCondition, String restrictedParticipant, Long views) {
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

    public static PolicyDetailResponseDto of(Policy policy) {
        return PolicyDetailResponseDto.builder()
                .name(policy.getName())
                .detail(policy.getDetail())
                .bizId(policy.getBizId())
                .field(policy.getField())
                .content(policy.getContent())
                .managePeriod(policy.getManagePeriod())
                .applicationPeriod(policy.getApplicationPeriod())
                .scale(policy.getScale())
                .ageInfoMin(policy.getAgeInfoMin())
                .ageInfoMax(policy.getAgeInfoMax())
                .residence(policy.getResidenceContent())
                .education(policy.getEducationContent())
                .jobStatus(policy.getJobStatusContent())
                .major(policy.getMajor())
                .specialization(policy.getSpecialization())
                .additionalCondition(policy.getAdditionalCondition())
                .restrictedParticipant(policy.getRestrictedParticipant())
                .views(policy.getViews())
                .build();
    }
}
