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
public class Residence {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ResidenceEnum name;

    public static Residence of(ResidenceEnum residenceEnum) {
        return Residence.builder()
                .name(residenceEnum)
                .build();
    }
}
