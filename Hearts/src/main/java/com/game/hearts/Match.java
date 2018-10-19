package com.game.hearts;

import com.game.hearts.enums.Player;
import com.game.hearts.objects.Game;
import com.game.hearts.utils.ScoringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A match consists of hands until one player reaches 100.
 * The player with least point wins
 */
public class Match {
    public static void main(String[] args) {
        Map<Player, Integer> scoreBoard = ScoringUtils.getInitialState();
        while (!hasMatchEnded(scoreBoard)) {
            System.out.println("Starting new Game");
            Game game = new Game();
            game.startGame();
            ScoringUtils.updateScore(scoreBoard, game.getPointsBoard());
        }
        System.out.println("Match Ended");
        Set<Player> winner = getWinner(scoreBoard);
        System.out.println("WINNER: " + winner.toString());
    }

    private static Set<Player> getWinner(Map<Player, Integer> scoreBoard) {
        Set<Player> winners = new HashSet<>();
        Integer minVal = Integer.MAX_VALUE;
        for (Map.Entry<Player, Integer> score : scoreBoard.entrySet()) {
            if (score.getValue().equals(minVal)) {
                winners.add(score.getKey());
            }
            if (score.getValue() < minVal) {
                minVal = score.getValue();
                winners = new HashSet<>();
                winners.add(score.getKey());
            }
        }
        return winners;
    }


    // Match ends when any players reaches 100
    private static boolean hasMatchEnded(Map<Player, Integer> scoreBoard) {
        return scoreBoard.values().stream().anyMatch(i -> i >= 100);
    }
}
