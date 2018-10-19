package com.game.hearts.enums;

import java.util.Arrays;

/**
 * Created by mdnmcg on 11/13/16.
 */
public enum Suit {

    HEARTS,
    DIAMONDS,
    SPADES,
    CLUBS;

    public static boolean contains(String test) {
        return Arrays.stream(Suit.values()).anyMatch(c -> c.name().equalsIgnoreCase(test));
    }

}
