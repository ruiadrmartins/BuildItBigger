package com.udacity.gradle.jokes;

import java.util.Random;

public class Jokes {

    public static String getJoke(int index) {
        return JokeRepo.jokes[index];
    }

    public static String getRandomJoke() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(JokeRepo.jokes.length);

        return JokeRepo.jokes[randomNumber];
    }
}
