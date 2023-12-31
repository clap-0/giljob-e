package com.likelion.giljobe.repository;

import com.likelion.giljobe.domain.*;
import com.likelion.giljobe.dto.PolicyListRequestDto;
import com.likelion.giljobe.dto.PolicyListResponseDto;
import com.likelion.giljobe.dto.QPolicyListResponseDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PolicyRepositoryCustomImpl implements PolicyRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    /**
     * 검색어와 필터링 조건을 통해 정책을 조회한다.
     *
     * @param requestDto - 검색어 및 필터링 조건이 포함된 요청값
     * @param pageable   - 페이지네이션을 위한 Pageable 인스턴스
     * @return - PolicyListResponseDto 인스턴스를 포함하는 Page 인스턴스
     */
    @Override
    public Optional<Page<PolicyListResponseDto>> findByFilter(PolicyListRequestDto requestDto, Pageable pageable) {

        QPolicy qPolicy = QPolicy.policy;

        List<PolicyListResponseDto> content = queryFactory
                .select(new QPolicyListResponseDto(
                        qPolicy.name,
                        qPolicy.bizId
                ))
                .from(qPolicy)
                .where(
                        keywordContains(requestDto.getKeyword(), qPolicy),
                        ageBtw(requestDto.getAge(), qPolicy),
                        educationIn(requestDto.getEducation(), qPolicy),
                        jobStatusIn(requestDto.getJobStatus(), qPolicy),
                        residenceIn(requestDto.getResidence(), qPolicy)
                )
                .orderBy(qPolicy.name.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Policy> countQuery = queryFactory
                .select(qPolicy)
                .from(qPolicy)
                .where(
                        keywordContains(requestDto.getKeyword(), qPolicy),
                        ageBtw(requestDto.getAge(), qPolicy),
                        educationIn(requestDto.getEducation(), qPolicy),
                        jobStatusIn(requestDto.getJobStatus(), qPolicy),
                        residenceIn(requestDto.getResidence(), qPolicy)
                );

        return Optional.of(PageableExecutionUtils
                .getPage(content, pageable, countQuery::fetchCount));
    }

    /**
     * 필터링 조건을 통해 정책을 정확히 pageable.pageSize 개수만큼 추천한다.
     *
     * @param requestDto - 검색어 및 필터링 조건이 포함된 요청값
     * @param pageable - 페이지네이션을 위한 Pageable 인스턴스
     * @return - PolicyListResponseDto 인스턴스를 포함하는 Page 인스턴스
     */
    @Override
    public Optional<Page<PolicyListResponseDto>> findByRecommendCondition(PolicyListRequestDto requestDto, Pageable pageable) {

        QPolicy qPolicy = QPolicy.policy;

        QueryResults<PolicyListResponseDto> results = queryFactory
                .select(new QPolicyListResponseDto(
                        qPolicy.name,
                        qPolicy.bizId
                ))
                .from(qPolicy)
                .where(
                        keywordContains(requestDto.getKeyword(), qPolicy),
                        ageBtw(requestDto.getAge(), qPolicy),
                        educationIn(requestDto.getEducation(), qPolicy),
                        jobStatusIn(requestDto.getJobStatus(), qPolicy),
                        residenceIn(requestDto.getResidence(), qPolicy)
                )
                .orderBy(qPolicy.name.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<PolicyListResponseDto> content = new ArrayList<>(results.getResults());
        long total = results.getTotal();

        // 조회된 개수가 요청된 개수보다 적다면 요청된 개수만큼으로 채워서 반환하도록 한다.
        if (content.size() < pageable.getPageSize()) {
            QueryResults<PolicyListResponseDto> additionalResults = queryFactory
                    .select(new QPolicyListResponseDto(
                            qPolicy.name,
                            qPolicy.bizId
                    ))
                    .from(qPolicy)
                    .where(
                            ageBtw(requestDto.getAge(), qPolicy),
                            qPolicy.policyEducationList.any().education.name.eq(EducationEnum.ALL),
                            qPolicy.policyJobStatusList.any().jobStatus.name.eq(JobStatusEnum.ALL),
                            qPolicy.policyResidenceList.any().residence.name.eq(ResidenceEnum.ALL)
                    )
                    .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                    .limit(pageable.getPageSize())
                    .fetchResults();

            content.addAll(additionalResults.getResults());
            content = content.subList(0, pageable.getPageSize());
            total += additionalResults.getTotal();
        }

        return Optional.of(new PageImpl<>(content, pageable, total));
    }

    /**
     * 주어진 검색어를 포함하는 Policy 엔티티를 확인한다.
     *
     * @param keyword - 검색어 문자열
     * @param qPolicy - QPolicy 인스턴스
     * @return - Predicate
     */
    private BooleanExpression keywordContains(String keyword, QPolicy qPolicy) {
        if (keyword == null) {
            return null;
        }
        return qPolicy.name.contains(keyword)
                .or(qPolicy.detail.contains(keyword)
                        .or(qPolicy.content.contains(keyword)));
    }

    /**
     * 주어진 age 값이 Policy 엔티티의 ageInfoMin 값과 ageInfoMax 값 사이인지 확인한다.
     *
     * @param age - 사잇값인지 확인할 나이
     * @param qPolicy - QPolicy 인스턴스
     * @return - Predicate
     */
    private BooleanExpression ageBtw(Integer age, QPolicy qPolicy) {
        if (age == null) {
            return null;
        }
        return qPolicy.ageInfoMin.loe(age).and(qPolicy.ageInfoMax.goe(age));
    }

    /**
     * 주어진 EducationEnum 값을 가지는 Policy 엔티티를 확인한다.
     *
     * @param educationEnum - 확인할 EducationEnum 인스턴스
     * @param qPolicy - QPolicy 인스턴스
     * @return - Predicate
     */
    private BooleanExpression educationIn(EducationEnum educationEnum, QPolicy qPolicy) {
        if (educationEnum == null || educationEnum == EducationEnum.ALL) {
            return null;
        }

        return qPolicy.policyEducationList.any()
                .education.name.eq(educationEnum);
    }

    /**
     * 주어진 JobStatusEnum 값을 가지는 Policy 엔티티를 확인한다.
     *
     * @param jobStatusEnum - 확인할 JobStatusEnum 인스턴스
     * @param qPolicy - QPolicy 인스턴스
     * @return - Predicate
     */
    private BooleanExpression jobStatusIn(JobStatusEnum jobStatusEnum, QPolicy qPolicy) {
        if (jobStatusEnum == null || jobStatusEnum == JobStatusEnum.ALL) {
            return null;
        }
        return qPolicy.policyJobStatusList.any()
                .jobStatus.name.eq(jobStatusEnum);
    }

    /**
     * 주어진 ResidenceEnum 값을 가지는 Policy 엔티티를 확인한다.
     *
     * @param residenceEnum - 확인할 ResidenceEnum 인스턴스
     * @param qPolicy - QPolicy 인스턴스
     * @return - Predicate
     */
    private BooleanExpression residenceIn(ResidenceEnum residenceEnum, QPolicy qPolicy) {
        if (residenceEnum == null || residenceEnum == ResidenceEnum.ALL) {
            return null;
        }
        return qPolicy.policyResidenceList.any()
                .residence.name.eq(residenceEnum);
    }
}
