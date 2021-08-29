package br.com.tonim.multiplication.challenge.eventpub;

import br.com.tonim.multiplication.challenge.DTOs.ChallengeSolvedEvent;
import br.com.tonim.multiplication.challenge.domain.ChallengeAttempt;
import br.com.tonim.multiplication.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ChallengeEventPubTest {
    ChallengeEventPub challengeEventPub;

    @Mock
    AmqpTemplate amqpTemplate;

    @BeforeEach
    public void setUp(){
        challengeEventPub = new ChallengeEventPub(amqpTemplate, "test.topic");
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void sendAttempts(Boolean correct) {
        var attempt = createTestAttempt(correct);

        challengeEventPub.challengeSolved(attempt);

        var exchangeCaptor = ArgumentCaptor.forClass(String.class);
        var routingKeyCaptor = ArgumentCaptor.forClass(String.class);
        var eventCaptor = ArgumentCaptor.forClass(ChallengeSolvedEvent.class);

        verify(amqpTemplate).convertAndSend(exchangeCaptor.capture(),routingKeyCaptor.capture(), eventCaptor.capture());
        then(exchangeCaptor.getValue()).isEqualTo("test.topic");
        then(routingKeyCaptor.getValue()).isEqualTo("attempt." + (correct ? "correct" : "wrong"));
        then(eventCaptor.getValue()).isEqualTo(solvedEvent(correct));
    }

    private Object solvedEvent(Boolean correct) {
        return new ChallengeSolvedEvent(1L, correct, 30, 40, 10L, "john");
    }

    private ChallengeAttempt createTestAttempt(Boolean correct) {
        return new ChallengeAttempt(1L, 30, 40, correct ? 1200 : 1300, correct, new User(10L, "john"));
    }

}