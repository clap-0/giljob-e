package com.likelion.giljobe.service;

import com.likelion.giljobe.domain.Education;
import com.likelion.giljobe.domain.EducationEnum;
import com.likelion.giljobe.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;

    /**
     * Education 엔티티를 저장하는 메서드
     *
     * @param educationEnum - Education 엔티티가 가지는 EducationEnum 인스턴스 값
     */
    public void save(EducationEnum educationEnum) {
        Education education = Education.of(educationEnum);

        this.educationRepository.save(education);
    }
}
