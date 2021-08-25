package br.com.tonim.multiplication.challenge.services;

import br.com.tonim.multiplication.challenge.DTOs.ChallengeAttemptDTO;
import br.com.tonim.multiplication.challenge.domain.ChallengeAttempt;
import br.com.tonim.multiplication.challenge.repositories.ChallengeAttemptRepository;
import br.com.tonim.multiplication.serviceclients.GamificationServiceClient;
import br.com.tonim.multiplication.user.domain.User;
import br.com.tonim.multiplication.user.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class ChallengeServiceImpl implements ChallengeService {

    private final UserRepository userRepository;
    private final ChallengeAttemptRepository attemptRepository;
    private final GamificationServiceClient gameClient;

    public ChallengeServiceImpl(UserRepository userRepository, ChallengeAttemptRepository attemptRepository,
                                GamificationServiceClient gameClient) {
        this.userRepository = userRepository;
        this.gameClient = gameClient;
        this.attemptRepository = attemptRepository;
    }

    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO) {
        var userAliasLowerCase = attemptDTO.getUserAlias().toLowerCase(Locale.ROOT);
        var user = userRepository.findByAlias(userAliasLowerCase)
                .orElseGet(() -> {
                    log.info("Creating new user with alias {}",
                            attemptDTO.getUserAlias());
                    var userToCreate = new User();
                    userToCreate.setAlias(userAliasLowerCase);
                    return userRepository.save(userToCreate);
                });

        var isCorrect = attemptDTO.getGuess() ==
                attemptDTO.getFactorA() * attemptDTO.getFactorB();

        var checkedAttempt = new ChallengeAttempt(null,
                attemptDTO.getFactorA(),
                attemptDTO.getFactorB(),
                attemptDTO.getGuess(),
                isCorrect,
                user
        );

        var storedAttempt = attemptRepository.save(checkedAttempt);
        var status = gameClient.sendAttempt(storedAttempt);
        log.info("Gamification service response: {}", status);
        return storedAttempt;
    }

    @Override
    public List<ChallengeAttempt> getStatsForUser(String userAlias) {
        return attemptRepository.findTop10ByUserAliasOrderByIdDesc(userAlias.toLowerCase(Locale.ROOT));
    }
}
