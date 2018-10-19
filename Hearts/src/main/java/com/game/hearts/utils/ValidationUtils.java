package com.game.hearts.utils;

import com.game.hearts.GameLogic;
import com.game.hearts.enums.CardValue;
import com.game.hearts.enums.Player;
import com.game.hearts.enums.Suit;
import com.game.hearts.objects.Card;
import com.game.hearts.objects.Game;

import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Created by mdnmcg on 11/13/16.
 */
public class ValidationUtils {

    public static boolean isValidCard(String input) {
        String[] inputArr = input.split("\\s");
        return Suit.contains(inputArr[1].toUpperCase()) && CardValue.contains(inputArr[0].toUpperCase());
    }

    public static boolean isValidMove(Card currentCard, LinkedHashMap<Player, Card> currentHand, Set<Card>
            playerCards, Game game) {
        boolean hasHeartsBeenPlayed = game.hasHeartsBeenPlayed();
        boolean isFirstMove = game.isFirstHand();
        if (!playerCards.contains(currentCard)) {
            System.out.println("You don't have that card");
            return false;
        }
        if (currentHand.size() == 0) {
            if (hasHeartsBeenPlayed) {
                return true;
            } else {
                if (currentCard.getSuit() != Suit.HEARTS) {
                    return true;
                } else {
                    return playerCards.stream().allMatch(card -> card.getSuit() == Suit.HEARTS);
                }
            }
        }
        Suit current = GameLogic.getCurrentSuit(currentHand);
        if (currentCard.getSuit().equals(current)) {
            return true;
        } else {
            if (currentCard.getSuit() == Suit.HEARTS) {
                if (isFirstMove) {
                    return !playerCards.stream().anyMatch(ca -> ca.getSuit() != Suit.HEARTS);
                }
            }
            return playerCards.stream().allMatch(ca -> ca.getSuit() != current);
        }
    }

}
