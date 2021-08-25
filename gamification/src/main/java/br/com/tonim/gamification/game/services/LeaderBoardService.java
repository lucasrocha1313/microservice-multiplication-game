package br.com.tonim.gamification.game.services;

import br.com.tonim.gamification.game.domain.LeaderBoardRow;

import java.util.List;

public interface LeaderBoardService {
    List<LeaderBoardRow> getCurrentLeaderBoard();
}
