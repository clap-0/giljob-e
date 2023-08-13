package com.likelion.giljobe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PolicySaveListRequestDto {

    private List<PolicySaveRequestDto> policies;

    private Integer totalCount;
}
