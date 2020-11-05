package com.example.android.wordgame.models;

public class QuizLevel {
    private int totalScore;
    private int collectedScore;
    private int levelNumber;
    private int scoreToUnlock;

    //FOR TEST
    public QuizLevel(int totalScore, int collectedScore, int levelNumber, int scoreToUnlock) {
        this.totalScore = totalScore;
        this.collectedScore = collectedScore;
        this.levelNumber = levelNumber;
        this.scoreToUnlock = scoreToUnlock;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getCollectedScore() {
        return collectedScore;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public int getScoreToUnlock() {
        return scoreToUnlock;
    }
}
