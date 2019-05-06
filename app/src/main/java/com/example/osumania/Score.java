package com.example.osumania;

public class Score {

    private static Score single_instance = null;

    private static double greatMargin, okMargin, badMargin, missMargin;
    private static int totalGreats, totalOks, totalBads, totalNotesHit, totalScore, combo;
    private static double greatScore, okScore, badScore, accuracy;

    public Score()
    {
        //milliseconds
        greatMargin = 50;
        okMargin = 80;
        badMargin = 100;
        missMargin = 120;

        greatScore = 300;
        okScore = 200;
        badScore = 100;
        initGameInstance();
    }
    private void initGameInstance(){
        totalGreats = 0;
        totalOks = 0;
        totalBads = 0;
        totalNotesHit = 0;
        accuracy = 0;
        totalScore = 0;
        combo = 0;
    }
    public void newGame(){
        initGameInstance();
    }
    public static Score getInstance()
    {
        if (single_instance == null)
            single_instance = new Score();

        return single_instance;
    }
    public int getTotalScore(){
        return totalScore;
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
    public int getCombo(){
        return combo;
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
    public void increaseCombo(){
        combo++;
    }
    public void resetCombo(){
        combo=0;
    }
    public void onGreatHit(){
        totalScore+= greatScore;
        totalGreats++;
        updateAccuracy(greatMargin);
    }
    public void onOkHit(){
        totalScore+= okScore;
        totalOks++;
        updateAccuracy(okMargin);
    }
    public void onBadHit(){
        totalScore+= badScore;
        totalBads++;
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
