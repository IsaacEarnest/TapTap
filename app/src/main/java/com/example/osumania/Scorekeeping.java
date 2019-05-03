package com.example.osumania;

public class Scorekeeping {

    int score = 0;

    public Scorekeeping(int score){
        this.score = score;
    }

    public void addScore(){
        score += 1;
    }

    public void resetScore(){
        score = 0;
    }

    public int getScore(){
        return score;
    }


}
