package br.com.tonim.multiplication.challenge.services;

import br.com.tonim.multiplication.challenge.domain.Challenge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ChallengeGeneratorServiceImplTest {
    private ChallengeGeneratorService challengeGeneratorService;

    @Spy
    private Random random;

    @BeforeEach
    public void setUp(){
        challengeGeneratorService = new ChallengeGeneratorServiceImpl(random);
    }

    @Test
    void generateRandomFactorIsBetweenExpectedLimits(){
        given(random.nextInt(89)).willReturn(20,30);

        var challenge = challengeGeneratorService.randomChallenge();

        then(challenge).isEqualTo(new Challenge(31,41));
    }

}