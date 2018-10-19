package com.game.hearts.objects;

import com.game.hearts.enums.CardValue;
import com.game.hearts.enums.Suit;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by mdnmcg on 11/13/16.
 */
@Data
@AllArgsConstructor
public class Card {
    CardValue cardValue;
    Suit suit;

    @Override
    public String toString() {
        return cardValue + " " + suit;
    }
}
