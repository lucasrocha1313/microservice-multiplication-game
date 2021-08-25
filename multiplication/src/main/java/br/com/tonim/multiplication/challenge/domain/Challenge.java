package br.com.tonim.multiplication.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Challenge {
    private Integer factorA;
    private Integer factorB;
}
