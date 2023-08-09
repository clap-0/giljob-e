package com.likelion.giljobe.repository;

import com.likelion.giljobe.domain.Education;
import com.likelion.giljobe.domain.EducationEnum;
import com.likelion.giljobe.domain.QEducation;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class EducationRepositoryCustomImpl implements EducationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Education> findByNames(List<EducationEnum> educationEnums) {
        QEducation qEducation = QEducation.education;

        BooleanBuilder builder = new BooleanBuilder();
        for (EducationEnum educationEnum : educationEnums) {
            builder.or(qEducation.name.eq(educationEnum));
        }

        return queryFactory
                .selectFrom(qEducation)
                .where(builder)
                .fetch();
    }
}
