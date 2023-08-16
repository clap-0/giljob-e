package com.likelion.giljobe.repository;

import com.likelion.giljobe.dto.PolicyListRequestDto;
import com.likelion.giljobe.dto.PolicyListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface PolicyRepositoryCustom {
    Optional<Page<PolicyListResponseDto>> findByFilter(PolicyListRequestDto requestDto, Pageable pageable);

    Optional<Page<PolicyListResponseDto>> findByRecommendCondition(PolicyListRequestDto requestDto, Pageable pageable);
}
