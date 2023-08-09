package com.likelion.giljobe.repository;

import com.likelion.giljobe.domain.Residence;
import com.likelion.giljobe.domain.ResidenceEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Long>, ResidenceRepositoryCustom {
}
