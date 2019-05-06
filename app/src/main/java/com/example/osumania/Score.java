package com.example.osumania;

public class Score {

    private static Score single_instance = null;

    static double greatMargin, okMargin, badMargin, missMargin;
    static int totalGreats, totalOks, totalBads, totalNotesHit, totalScore;
    static double greatScore, okScore, badScore, accuracy;

    public Score()
    {
        greatMargin = 100;
        okMargin = 160;
        badMargin = 200;
        missMargin = 240;
        totalGreats = 0;
        totalOks = 0;
        totalBads = 0;
        totalNotesHit = 0;
        greatScore = 300;
        okScore = 200;
        badScore = 100;
        accuracy = 0;
        totalScore = 0;
    }

    public static Score getInstance()
    {
        if (single_instance == null)
            single_instance = new Score();

        return single_instance;
    }
    public int getTotalGreats(){
        return totalGreats;
    }
    public int getTotalOks(){
        return totalOks;
    }

    public int getTotalBads() {
        return totalBads;
    }

    public int getTotalNotesHit() {
        return totalNotesHit;
    }
    public double getGreatMargin(){
        return greatMargin;
    }

    public double getOkMargin() {
        return okMargin;
    }

    public double getBadMargin() {
        return badMargin;
    }

    public double getMissMargin() {
        return missMargin;
    }

    public double getGreatScore() {
        return greatScore;
    }

    public double getOkScore() {
        return okScore;
    }

    public double getBadScore() {
        return badScore;
    }
    public int getTotalMisses(){
        int misses = totalNotesHit-totalGreats-totalOks-totalBads;
        return misses;
    }
    public void onGreatHit(){
        totalScore+= greatScore;
        updateAccuracy(greatMargin);
    }
    public void onOkHit(){
        totalScore+= okScore;
        updateAccuracy(okMargin);
    }
    public void onBadHit(){
        totalScore+= badScore;
        updateAccuracy(badMargin);
    }
    public void onMiss(){
        updateAccuracy(missMargin);
    }
    void updateAccuracy(double acc){
        double toPercent = 100.;
        if(acc==greatMargin)totalGreats++;
        else if(acc==okMargin)totalOks++;
        else if(acc==badMargin)totalBads++;
        accuracy = (toPercent*((greatScore*totalGreats)+(okScore*totalOks)+(badScore*totalBads)))/(greatScore*totalNotesHit);
        totalNotesHit++;
    }
    public static double getAccuracy(){
        return accuracy;
    }
}
