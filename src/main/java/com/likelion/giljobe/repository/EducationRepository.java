package com.likelion.giljobe.repository;

import com.likelion.giljobe.domain.Education;
import com.likelion.giljobe.domain.EducationEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long>, EducationRepositoryCustom {
}
