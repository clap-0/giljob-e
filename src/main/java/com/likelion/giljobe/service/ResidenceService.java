package com.likelion.giljobe.service;

import com.likelion.giljobe.domain.Residence;
import com.likelion.giljobe.domain.ResidenceEnum;
import com.likelion.giljobe.repository.ResidenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResidenceService {

    private final ResidenceRepository residenceRepository;

    /**
     * Residence 엔티티를 저장하는 메서드
     *
     * @param residenceEnum - Residence 엔티티가 가지는 ResidenceEnum 인스턴스 값
     */
    public void save(ResidenceEnum residenceEnum) {
        Residence residence = Residence.of(residenceEnum);

        this.residenceRepository.save(residence);
    }
}
