package br.com.tonim.gamification.game.services;

import br.com.tonim.gamification.challenge.DTOs.ChallengeSolvedEvent;
import br.com.tonim.gamification.game.domain.BadgeType;
import lombok.Value;

import java.util.List;

public interface GameService {
    GameResult newAttemptForUser(ChallengeSolvedEvent challenge);

    @Value
    class GameResult {
        int score;
        List<BadgeType> badges;
    }
}
