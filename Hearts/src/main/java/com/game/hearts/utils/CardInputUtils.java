package com.game.hearts.utils;

import com.game.hearts.enums.CardValue;
import com.game.hearts.enums.Suit;
import com.game.hearts.objects.Card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by mdnmcg on 11/13/16.
 */
public class CardInputUtils {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static Card getInputCard() {
        try {
            String input = br.readLine();
            while (!ValidationUtils.isValidCard(input)) {
                System.out.println("There is no such card: " + input);
                UserMessages.printCardFormat();
                input = br.readLine();
            }
            String[] inputArr = input.split("\\s");
            return new Card(CardValue.valueOf(inputArr[0].toUpperCase()), Suit.valueOf(inputArr[1].toUpperCase()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
