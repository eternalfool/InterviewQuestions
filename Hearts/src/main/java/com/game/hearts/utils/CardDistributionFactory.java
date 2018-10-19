package com.game.hearts.utils;

import com.game.hearts.enums.CardValue;
import com.game.hearts.enums.Suit;
import com.game.hearts.objects.Card;
import com.game.hearts.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdnmcg on 11/13/16.
 */
public class CardDistributionFactory {

    public static List<Card> getShuffledCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values())
            for (CardValue cardValue : CardValue.values())
                cards.add(new Card(cardValue, suit));
        Utils.fisherYatesShuffle(cards);
        return cards;
    }
}
