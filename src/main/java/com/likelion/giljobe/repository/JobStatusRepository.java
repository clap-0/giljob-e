package com.likelion.giljobe.repository;

import com.likelion.giljobe.domain.JobStatus;
import com.likelion.giljobe.domain.JobStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobStatusRepository extends JpaRepository<JobStatus, Long>, JobStatusRepositoryCustom {
}
