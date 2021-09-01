package br.com.tonim.gamification.game.eventhandler;

import br.com.tonim.gamification.challenge.DTOs.ChallengeSolvedEvent;
import br.com.tonim.gamification.game.services.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GameEventHandler {
    private final GameService gameService;

    public GameEventHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @RabbitListener(queues = "${amqp.queue.gamification}")
    void handleMultiplicationSolved(final ChallengeSolvedEvent challengeSolvedEvent) {
        log.info("Challenge solved event received: {}", challengeSolvedEvent.getAttemptId());
        try {
            gameService.newAttemptForUser(challengeSolvedEvent);

        } catch (final Exception ex) {
            log.error("Error trying to process ChallengeSolvedEvent", ex);
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
