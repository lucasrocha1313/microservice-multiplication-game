package br.com.tonim.gamification.game.badgeprocessors;

import br.com.tonim.gamification.challenge.DTOs.ChallengeSolvedEvent;
import br.com.tonim.gamification.game.domain.BadgeType;
import br.com.tonim.gamification.game.domain.ScoreCard;

import java.util.List;
import java.util.Optional;

public interface BadgeProcessor {
    Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedEvent solved);
    BadgeType badgeType();
}
