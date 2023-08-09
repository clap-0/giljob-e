package com.likelion.giljobe.repository;

import com.likelion.giljobe.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long>, PolicyRepositoryCustom {
}
