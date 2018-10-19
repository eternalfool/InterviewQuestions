package com.game.hearts;

import com.game.hearts.enums.CardValue;
import com.game.hearts.enums.Player;
import com.game.hearts.enums.Suit;
import com.game.hearts.objects.Card;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static com.game.hearts.enums.CardValue.isGreater;
import static com.game.hearts.utils.Constants.QUEEN_OF_SPADES;
import static com.game.hearts.utils.Constants.TWO_OF_CLUBS;

/**
 * Created by mdnmcg on 11/14/16.
 */
public class GameLogic {

    public static int calculatePoints(LinkedHashMap<Player, Card> currentHand) {
        int points = 0;
        for (Map.Entry<Player, Card> entry : currentHand.entrySet()) {
            Card card = entry.getValue();
            if (card.getSuit() == Suit.HEARTS) {
                points += 1;
            }
            if (card.equals(QUEEN_OF_SPADES)) {
                points += 13;
            }
        }
        return points;
    }

    // player with 2 of clubs starts the game
    public static Player getFirstTurn(Map<Player, Set<Card>> playerToCards) {
        for (Map.Entry<Player, Set<Card>> entry : playerToCards.entrySet()) {
            if (entry.getValue().contains(TWO_OF_CLUBS))
                return entry.getKey();
        }
        return null;
    }

    public static Player getWinner(LinkedHashMap<Player, Card> currentHand) {
        Map.Entry<Player, Card> first = getFirstEntry(currentHand);
        Suit current = first.getValue().getSuit();
        CardValue currentMax = first.getValue().getCardValue();
        Player winner = first.getKey();
        for (Map.Entry<Player, Card> entry : currentHand.entrySet()) {
            Card card = entry.getValue();
            if (card.getSuit().equals(current) && isGreater(card.getCardValue(), currentMax)) {
                currentMax = card.getCardValue();
                winner = entry.getKey();
            }
        }
        return winner;
    }

    private static Map.Entry<Player, Card> getFirstEntry(LinkedHashMap<Player, Card> currentHand) {
        return currentHand.entrySet().iterator().next();
    }

    public static Suit getCurrentSuit(LinkedHashMap<Player, Card> currentHand) {
        if (currentHand.size() == 0) return null;
        return currentHand.entrySet().iterator().next().getValue().getSuit();
    }

}
