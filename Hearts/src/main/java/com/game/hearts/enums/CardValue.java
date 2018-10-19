package com.game.hearts.enums;

import java.util.Arrays;

/**
 * Created by mdnmcg on 11/13/16.
 */
public enum CardValue {

    ACE(14),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13);

    private int value;

    private CardValue(int value) {
        this.value = value;
    }

    public static boolean contains(String test) {
        return Arrays.stream(CardValue.values()).anyMatch(c -> c.name().equalsIgnoreCase(test));
    }

    public static boolean isGreater(CardValue firstCard, CardValue anotherCard) {
        return firstCard.value > anotherCard.value;
    }

}
