package com.game.hearts.utils;

import java.util.List;
import java.util.Random;

/**
 * Created by mdnmcg on 11/13/16.
 */
public class Utils {

    public static <T> void fisherYatesShuffle(List<T> list) {
        int index;
        T temp;
        Random random = new Random();
        for (int i = list.size() - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = list.get(index);
            list.set(index, list.get(i));
            list.set(i, temp);
        }
    }
}
