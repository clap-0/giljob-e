package com.likelion.giljobe.repository;

import com.likelion.giljobe.dto.PolicyFindRequestDto;
import com.likelion.giljobe.dto.PolicyFindResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface PolicyRepositoryCustom {
    Optional<Page<PolicyFindResponseDto>> findByFilter(PolicyFindRequestDto requestDto, Pageable pageable);
}
