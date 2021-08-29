package br.com.tonim.multiplication.challenge.eventpub;

import br.com.tonim.multiplication.challenge.DTOs.ChallengeSolvedEvent;
import br.com.tonim.multiplication.challenge.domain.ChallengeAttempt;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChallengeEventPub {
    private final AmqpTemplate amqpTemplate;
    private final String challengesTopicExchange;

    public ChallengeEventPub(AmqpTemplate amqpTemplate, @Value("${amqp.exchange.attempts}")String challengesTopicExchange) {
        this.amqpTemplate = amqpTemplate;
        this.challengesTopicExchange = challengesTopicExchange;
    }

    public void challengeSolved(final ChallengeAttempt challengeAttempt) {
        var event = buildEvent(challengeAttempt);
        var routingKey = String.format("attempt.%s", (event.isCorrect() ? "correct":"wrong"));
        amqpTemplate.convertAndSend(challengesTopicExchange,routingKey,event);
    }

    private ChallengeSolvedEvent buildEvent(final ChallengeAttempt challengeAttempt) {
        return new ChallengeSolvedEvent(challengeAttempt.getId(),challengeAttempt.isCorrect(), challengeAttempt.getFactorA(),
                challengeAttempt.getFactorB(), challengeAttempt.getUser().getId(), challengeAttempt.getUser().getAlias());
    }
}
