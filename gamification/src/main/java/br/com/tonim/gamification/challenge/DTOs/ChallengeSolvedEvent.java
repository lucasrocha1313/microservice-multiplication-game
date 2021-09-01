package br.com.tonim.gamification.challenge.DTOs;

import lombok.Value;

@Value
public class ChallengeSolvedEvent {
    long attemptId;
    boolean correct;
    int factorA;
    int factorB;
    long userId;
    String userAlias;
}
