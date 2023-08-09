package com.likelion.giljobe.repository;

import com.likelion.giljobe.dto.PolicyFindRequestDto;
import com.likelion.giljobe.dto.PolicyFindResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PolicyRepositoryCustom {
    Page<PolicyFindResponseDto> findByFilter(PolicyFindRequestDto requestDto, Pageable pageable);
}
