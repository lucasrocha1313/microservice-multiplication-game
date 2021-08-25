package br.com.tonim.gamification.game.services;

import br.com.tonim.gamification.game.domain.LeaderBoardRow;
import br.com.tonim.gamification.game.repositories.BadgeRepository;
import br.com.tonim.gamification.game.repositories.ScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private final ScoreRepository scoreRepository;
    private final BadgeRepository badgeRepository;

    public LeaderBoardServiceImpl(ScoreRepository scoreRepository, BadgeRepository badgeRepository) {
        this.scoreRepository = scoreRepository;
        this.badgeRepository = badgeRepository;
    }

    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        var scoreOnly = scoreRepository.findFirst10();

        return scoreOnly.stream().map(this::getLeaderBoardRowWithBadges).collect(Collectors.toList());
    }

    private LeaderBoardRow getLeaderBoardRowWithBadges(LeaderBoardRow leaderBoardRow) {
        var badges = badgeRepository.findByUserIdOrderByBadgeTimestampDesc(leaderBoardRow.getUserId())
                .stream().map(badgeCard -> badgeCard.getBadgeType().getDescription()).collect(Collectors.toList());
        return leaderBoardRow.withBadges(badges);
    }
}
