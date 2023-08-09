package com.likelion.giljobe.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PolicyResidence {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residence_id")
    private Residence residence;

    public static PolicyResidence create(Policy policy, Residence residence) {
        PolicyResidence policyResidence = new PolicyResidence();
        policyResidence.setPolicy(policy);
        policyResidence.setResidence(residence);

        policy.getPolicyResidenceList().add(policyResidence);

        return policyResidence;
    }
}
