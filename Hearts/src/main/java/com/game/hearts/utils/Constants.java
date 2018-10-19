package com.game.hearts.utils;

import com.game.hearts.enums.CardValue;
import com.game.hearts.enums.Suit;
import com.game.hearts.objects.Card;

/**
 * Created by mdnmcg on 11/13/16.
 */
public interface Constants {
    Card TWO_OF_CLUBS = new Card(CardValue.TWO, Suit.CLUBS);
    Card QUEEN_OF_SPADES = new Card(CardValue.QUEEN, Suit.SPADES);
}
