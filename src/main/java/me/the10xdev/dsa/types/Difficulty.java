package me.the10xdev.dsa.types;

import java.util.Arrays;

public enum Difficulty {

    EASY(1),
    MEDIUM(2),
    HARD(3);

    private final int difficulty;

    Difficulty(int difficulty) {
       this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public static Difficulty of(int difficulty) {
        //TODO: Review exception
        return Arrays.stream(Difficulty.values()).filter(diff -> diff.getDifficulty() == difficulty).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
