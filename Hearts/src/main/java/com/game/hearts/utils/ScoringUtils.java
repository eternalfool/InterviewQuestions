package com.game.hearts.utils;

import com.game.hearts.enums.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by mdnmcg on 11/14/16.
 */
public class ScoringUtils {
    public static Map<Player, Integer> getInitialState() {
        Map<Player, Integer> scoreBoard = new HashMap<>();
        IntStream.rangeClosed(1, 4).forEach(i -> scoreBoard.put(Player.valueOf(i), 0));
        return scoreBoard;
    }

    public static void updateScore(Map<Player, Integer> scoreBoard, Map<Player, Integer> pointBoard) {
        checkForMoonShot(pointBoard);
        for (Map.Entry<Player, Integer> score : scoreBoard.entrySet()) {
            score.setValue(score.getValue() + pointBoard.get(score.getKey()));
        }
    }

    private static void checkForMoonShot(Map<Player, Integer> pointsBoard) {
        if (pointsBoard.values().stream().anyMatch(x -> x == 26)) {
            for (Map.Entry<Player, Integer> points : pointsBoard.entrySet()) {
                if (points.getValue() == 26) {
                    points.setValue(0);
                } else {
                    points.setValue(26);
                }
            }
        }
    }
}
