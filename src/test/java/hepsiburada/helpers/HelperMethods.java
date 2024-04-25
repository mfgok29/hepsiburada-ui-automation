package hepsiburada.helpers;

import java.util.Random;

public class HelperMethods {


    public static int getRandomIntInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static int getRandomIndex(int number) {

        Random r = new Random();
        return r.nextInt(number);
    }
}
