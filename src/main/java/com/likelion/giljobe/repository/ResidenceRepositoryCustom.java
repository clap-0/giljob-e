package com.likelion.giljobe.repository;

import com.likelion.giljobe.domain.Residence;
import com.likelion.giljobe.domain.ResidenceEnum;

import java.util.List;


public interface ResidenceRepositoryCustom {

    List<Residence> findByNames(List<ResidenceEnum> residenceEnums);
}
