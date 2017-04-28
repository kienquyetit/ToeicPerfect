package com.app.learningtoeic.utils;

import com.app.learningtoeic.entity.Word;

import java.util.List;
import java.util.Random;

/**
 * Created by dell on 4/24/2017.
 */

public class SuffleHelper {

    public static void shuffleWordArray(List<Word> ar)
    {
        Random rnd = new Random();
        for (int i = ar.size() - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            Word a = ar.get(index);
            ar.set(index,ar.get(i));
            ar.set(i,a);
        }
    }

    public static void shuffleIntArray(List<Integer> ar)
    {
        Random rnd = new Random();
        for (int i = ar.size() - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = ar.get(index);
            ar.set(index,ar.get(i));
            ar.set(i,a);
        }
    }

    public static void suffleStringArray(List<String> ar)
    {
        Random rnd = new Random();
        for (int i = ar.size() - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            String a = ar.get(index);
            ar.set(index,ar.get(i));
            ar.set(i,a);
        }
    }
}
