package br.com.tonim.multiplication.challenge.services;

import br.com.tonim.multiplication.challenge.DTOs.ChallengeAttemptDTO;
import br.com.tonim.multiplication.challenge.domain.ChallengeAttempt;

import java.util.List;

public interface ChallengeService {
    ChallengeAttempt verifyAttempt(ChallengeAttemptDTO resultAttempt);
    List<ChallengeAttempt> getStatsForUser(String userAlias);
}
