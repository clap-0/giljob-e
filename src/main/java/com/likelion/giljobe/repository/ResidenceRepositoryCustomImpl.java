package com.likelion.giljobe.repository;

import com.likelion.giljobe.domain.QResidence;
import com.likelion.giljobe.domain.Residence;
import com.likelion.giljobe.domain.ResidenceEnum;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ResidenceRepositoryCustomImpl implements ResidenceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Residence> findByNames(List<ResidenceEnum> residenceEnums) {
        QResidence qResidence = QResidence.residence;

        BooleanBuilder builder = new BooleanBuilder();
        for (ResidenceEnum residenceEnum : residenceEnums) {
            builder.or(qResidence.name.eq(residenceEnum));
        }

        return queryFactory
                .selectFrom(qResidence)
                .where(builder)
                .fetch();
    }
}
