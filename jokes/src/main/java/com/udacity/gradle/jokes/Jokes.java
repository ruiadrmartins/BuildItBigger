package com.udacity.gradle.jokes;

import java.util.Random;

/**
 * Object to handle jokes
 */
public class Jokes {

    /**
     * Get specific joke by index number
     * @param index
     * @return
     */
    public static String getJoke(int index) {
        return JokeRepo.jokes[index];
    }

    /**
     * Get random joke
     * @return
     */
    public static String getRandomJoke() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(JokeRepo.jokes.length);

        return JokeRepo.jokes[randomNumber];
    }
}
