package com.game.hearts.objects;

import com.game.hearts.utils.CardDistributionFactory;
import com.game.hearts.GameLogic;
import com.game.hearts.enums.Player;
import com.game.hearts.enums.Suit;
import com.game.hearts.utils.CardInputUtils;
import com.game.hearts.utils.ScoringUtils;

import java.util.*;
import java.util.stream.IntStream;

import static com.game.hearts.utils.Constants.TWO_OF_CLUBS;
import static com.game.hearts.utils.ValidationUtils.isValidMove;

/**
 * Created by mdnmcg on 11/13/16.
 */
public class Game {

    private Map<Player, Set<Card>> playerToCards;
    private Player currentTurn;
    private LinkedHashMap<Player, Card> currentHand = new LinkedHashMap<>();
    private Map<Player, Integer> pointsBoard = new HashMap<>();
    private boolean hasHeartsBeenPlayed = false;
    private boolean isFirstHand;

    public Game() {
        initializePointsBoard();
        distributeCards();
    }

    public void startGame() {
        playFirstMove();
        play();
    }


    // First move is always two of clubs
    private void playFirstMove() {
        currentTurn = GameLogic.getFirstTurn(playerToCards);
        playerToCards.get(currentTurn).remove(TWO_OF_CLUBS);
        currentHand.put(currentTurn, TWO_OF_CLUBS);
        System.out.println("Player: " + currentTurn + " has started the game with two of clubs");
        currentTurn = currentTurn.next();
    }

    // Initialize points of all players to 0
    private void initializePointsBoard() {
        pointsBoard = ScoringUtils.getInitialState();
    }

    private void distributeCards() {
        playerToCards = new HashMap<>();
        List<Card> deck = CardDistributionFactory.getShuffledCards();
        IntStream.rangeClosed(0, 3).forEach(i -> playerToCards.put(Player.valueOf(i + 1), new HashSet<>(deck.subList(i *
                        13,
                i * 13 + 13))));
        System.out.println("Card Distribution: \n" + playerToCards.toString());
    }

    private void play() {
        while (!hasGameEnded(playerToCards)) {
            playHand();
        }
        System.out.println("GAME OVER");
        System.out.println("SCORE: \n");
        System.out.println(pointsBoard.toString());
    }

    private void playHand() {
        while (currentHand.size() < 4) {
            System.out.println("Player: " + currentTurn + " turn");
            Card card = CardInputUtils.getInputCard();
            if (!isValidMove(card, currentHand, playerToCards.get(currentTurn), this)) {
                System.out.println("INVALID MOVE: ");
                continue;
            } else {
                playerToCards.get(currentTurn).remove(card);
                currentHand.put(currentTurn, card);
                currentTurn = currentTurn.next();
            }
        }
        isFirstHand = false;
        Player winner = GameLogic.getWinner(currentHand);
        System.out.println("Winner: " + winner.name());
        checkForHeartsPlay();
        updatePoints(winner);

        // change turn
        currentTurn = winner;
        // flush current hand
        currentHand = new LinkedHashMap<>();
    }

    private void updatePoints(Player winner) {
        int points = GameLogic.calculatePoints(currentHand);
        pointsBoard.put(winner, pointsBoard.get(winner) + points);
        System.out.println("Updated points tally: " + pointsBoard.toString());
    }


    // Hearts can only be played only when it has been previously given / no other choice
    private void checkForHeartsPlay() {
        if (!hasHeartsBeenPlayed) {
            if (isHeartsPresent(currentHand)) {
                hasHeartsBeenPlayed = true;
            }
        }
    }

    private boolean isHeartsPresent(LinkedHashMap<Player, Card> currentHand) {
        return currentHand.values().stream().anyMatch(card -> card.getSuit() == Suit.HEARTS);
    }

    private boolean hasGameEnded(Map<Player, Set<Card>> playerToCards) {
        for (Map.Entry<Player, Set<Card>> entry : playerToCards.entrySet()) {
            if (entry.getValue().size() > 0) return false;
        }
        return true;
    }

    public boolean hasHeartsBeenPlayed() {
        return hasHeartsBeenPlayed;
    }

    public boolean isFirstHand() {
        return isFirstHand;
    }

    public Map<Player, Integer> getPointsBoard() {
        return pointsBoard;
    }
}
