package br.com.tonim.gamification.game.badgeprocessors;

import br.com.tonim.gamification.challenge.DTOs.ChallengeSolvedEvent;
import br.com.tonim.gamification.game.domain.BadgeType;
import br.com.tonim.gamification.game.domain.ScoreCard;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SilverBadgeProcessor implements BadgeProcessor{

    @Override
    public Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedEvent solved) {
        return currentScore > 150? Optional.of(BadgeType.SILVER): Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.SILVER;
    }
}
