package com.game.hearts.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mdnmcg on 11/13/16.
 */
public enum Player {

    PLAYER_ONE(1),
    PLAYER_TWO(2),
    PLAYER_THREE(3),
    PLAYER_FOUR(4);

    private static Map<Integer, Player> reverseMap;

    static {
        reverseMap = Arrays.stream(Player.values()).collect(Collectors.toMap(Player::getValue, x -> x));
    }

    private int value;

    private Player(int value) {
        this.value = value;
    }

    public static Player valueOf(Integer value) {
        return reverseMap.get(value);
    }

    public int getValue() {
        return value;
    }

    public Player next() {
        return reverseMap.get(getNext((this.value)));
    }

    private Integer getNext(int value) {
        return (value == 4) ? 1 : (value + 1);
    }
}
