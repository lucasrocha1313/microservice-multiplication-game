package br.com.tonim.multiplication.challenge.repositories;

import br.com.tonim.multiplication.challenge.domain.ChallengeAttempt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChallengeAttemptRepository extends CrudRepository<ChallengeAttempt, Long> {
    List<ChallengeAttempt> findTop10ByUserAliasOrderByIdDesc(String userAlias);
}
