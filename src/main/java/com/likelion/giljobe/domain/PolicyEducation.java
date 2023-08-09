package com.likelion.giljobe.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PolicyEducation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "education_id")
    private Education education;

    public static PolicyEducation create(Policy policy, Education education) {
        PolicyEducation policyEducation = new PolicyEducation();
        policyEducation.setPolicy(policy);
        policyEducation.setEducation(education);

        policy.getPolicyEducationList().add(policyEducation);

        return policyEducation;
    }
}
