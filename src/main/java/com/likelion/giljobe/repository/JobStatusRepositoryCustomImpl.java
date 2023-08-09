package com.likelion.giljobe.repository;

import com.likelion.giljobe.domain.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class JobStatusRepositoryCustomImpl implements JobStatusRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<JobStatus> findByNames(List<JobStatusEnum> jobStatusEnums) {
        QJobStatus qJobStatus = QJobStatus.jobStatus;

        BooleanBuilder builder = new BooleanBuilder();
        for (JobStatusEnum jobStatusEnum : jobStatusEnums) {
            builder.or(qJobStatus.name.eq(jobStatusEnum));
        }

        return queryFactory
                .selectFrom(qJobStatus)
                .where(builder)
                .fetch();
    }
}
