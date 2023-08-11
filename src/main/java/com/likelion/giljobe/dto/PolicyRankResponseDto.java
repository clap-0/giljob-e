package com.likelion.giljobe.dto;

import com.likelion.giljobe.domain.Policy;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PolicyRankResponseDto {


    @ApiParam(value = "정책명")
    @Schema(description = "정책명", example = "사회적경제 문화예술 청년창업지원 프로젝트")
    private String name;

    @ApiParam(value = "정책번호")
    @Schema(description = "정책번호", example = "R2023050912273")
    private String bizId;

    @ApiParam(value = "조회수")
    @Schema(description = "조회수", example = "1")
    private Long views;

    public static PolicyRankResponseDto of(Policy policy) {
        return PolicyRankResponseDto.builder()
                .name(policy.getName())
                .bizId(policy.getBizId())
                .views(policy.getViews())
                .build();
    }
}
