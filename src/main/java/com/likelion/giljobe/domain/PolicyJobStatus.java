package com.likelion.giljobe.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PolicyJobStatus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_status_id")
    private JobStatus jobStatus;

    public static PolicyJobStatus create(Policy policy, JobStatus jobStatus) {
        PolicyJobStatus policyJobStatus = new PolicyJobStatus();
        policyJobStatus.setPolicy(policy);
        policyJobStatus.setJobStatus(jobStatus);

        System.out.println("policy.getPolicyJobStatusList() = " + policy.getPolicyJobStatusList());
        
        policy.getPolicyJobStatusList().add(policyJobStatus);

        return policyJobStatus;
    }
}
