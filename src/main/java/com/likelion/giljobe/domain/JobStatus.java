package com.likelion.giljobe.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobStatus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private JobStatusEnum name;

    public static JobStatus of(JobStatusEnum jobStatusEnum) {
        return JobStatus.builder()
                .name(jobStatusEnum)
                .build();
    }
}
