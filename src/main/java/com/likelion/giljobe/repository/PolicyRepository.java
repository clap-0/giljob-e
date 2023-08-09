package com.likelion.giljobe.repository;

import com.likelion.giljobe.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long>, PolicyRepositoryCustom {
    Optional<Object> findByBizId(String bizId);
}
