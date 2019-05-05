package com.example.osumania;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    private double greatMargin, okMargin, badMargin, missMargin;
    private int totalGreats, totalOks, totalBads;
    private double greatScore, okScore, badScore;
    private int scrollSpeed;
    enum keys{firstK, secondK, thirdK, fourthK}
    final String TAG = "GameClass";
    private double startTime;
    private double accuracy;
    private int totalNotesHit;
    private int score;
    ArrayList<Integer> first,second,third,fourth;
    ArrayList<Notes> notes;

    public Game(InputStream input) throws IOException {

        initVariables();
        initArrayLists();
        parseSongFile(input);
    }

    private void initVariables(){
        this.scrollSpeed = 50;
        greatMargin = 200;
        greatScore = 300;
        okMargin = 400;
        okScore = 200;
        badMargin = 600;
        badScore = 100;
        missMargin = 1500;
        startTime = System.currentTimeMillis();
    }

    private void initArrayLists(){
        first = new ArrayList<>();
        second = new ArrayList<>();
        third = new ArrayList<>();
        fourth = new ArrayList<>();
        notes = new ArrayList<>();
    }
    public int getCurTimeMil(){
        return (int)((System.currentTimeMillis())-startTime);
    }
    public ArrayList<ArrayList<Integer>> getAllNotes(){
        ArrayList<ArrayList<Integer>> allNotes = new ArrayList<>();
        allNotes.add(first);
        allNotes.add(second);
        allNotes.add(third);
        allNotes.add(fourth);
        return allNotes;
    }

    public ArrayList<Integer> getFirstRow(){
        return (ArrayList<Integer>)first.clone();
    }

    public ArrayList<Integer> getSecondRow(){
        return (ArrayList<Integer>)second.clone();
    }

    public ArrayList<Integer> getThirdRow(){
        return (ArrayList<Integer>)third.clone();
    }

    public ArrayList<Integer> getFourthRow(){
        return (ArrayList<Integer>)fourth.clone();
    }

    //TODO maybe unit testable? idk
    public boolean wasMiss(keys pos){
        return findHitAcc(pos).equals("miss");
    }

    public boolean wasTest(keys pos){
        return findHitAcc(pos).equals("test");
    }

    private double getCurrentTime() {
        return System.currentTimeMillis() - startTime + 14 * scrollSpeed - 1000;
    }
    //TODO score should really be a singleton class
    public double getAccuracy(){
        return accuracy;
    }
    public int getTotalGreats(){
        return totalGreats;
    }
    public int getTotalOks(){
        return totalOks;
    }
    public int getTotalBads(){
        return totalBads;
    }
    public int getTotalMisses(){
        int misses = totalNotesHit-totalGreats-totalOks-totalBads;
        return misses;
    }

    private String hitMarginString(ArrayList<Integer> position) {
        double currentTime = getCurrentTime();
        for (Integer i : position) {
            Log.d(TAG, "your hit = " + currentTime+", note was at "+i+". System is seeing "+Math.abs(i - currentTime)+"ms difference");
            if (Math.abs(i - currentTime) < greatMargin) {
                Log.d(TAG,"returning great");
                position.remove(i);
                calcAccuracy(greatMargin);
                score += greatScore;
                return "great";
            }
            if (Math.abs(i - currentTime) < okMargin) {
                Log.d(TAG,"returning ok");
                position.remove(i);
                calcAccuracy(okMargin);
                score += okScore;
                return "ok";
            }
            if (Math.abs(i - currentTime) < badMargin) {
                Log.d(TAG,"returning bad");
                position.remove(i);
                calcAccuracy(badMargin);
                score += badScore;
                return "bad";
            }
            if (Math.abs(i - currentTime) < missMargin) {
                Log.d(TAG,"returning miss");
                position.remove(i);
                calcAccuracy(missMargin);
                return "miss";
            }
        }
        Log.d(TAG,"returning test");
        return "test";
    }
    //TODO unit test this
    private void calcAccuracy(double acc){
        totalNotesHit++;
        if(acc==greatMargin)totalGreats++;
        else if(acc==okMargin)totalOks++;
        else if(acc==badMargin)totalBads++;
        accuracy = (greatScore*totalGreats+okScore*totalOks+badScore*totalBads)/(greatScore*totalNotesHit);
    }
    private String findHitAcc(keys pos) throws NullPointerException {
        if (pos.equals(keys.firstK)) {
            return hitMarginString(first);
        } else if (pos.equals(keys.secondK)) {
            return hitMarginString(second);
        } else if (pos.equals(keys.thirdK)) {
            return hitMarginString(third);
        } else if (pos.equals(keys.fourthK)) {
            return hitMarginString(fourth);
        }
        return "test";
    }

    public void parseSongFile(InputStream input) throws IOException {
        //Log.d(TAG,"parsing");
        String line = "";
        String firstRow = "64";
        String secondRow = "192";
        String thirdRow = "320";
        String fourthRow = "448";
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            //Log.d(TAG, "File open for business!");
            //Skipping lines which aren't related to hitObjects
            while (!reader.readLine().equals("[HitObjects]")) {}
            //Adding Notes
            while ((line = reader.readLine()) != null) {
                if (line.split(",")[0].equals(firstRow)) {
                    first.add(Integer.parseInt(line.split(",")[2]));
                }if (line.split(",")[0].equals(secondRow)) {
                    second.add(Integer.parseInt(line.split(",")[2]));
                }if (line.split(",")[0].equals(thirdRow)) {
                    third.add(Integer.parseInt(line.split(",")[2]));
                }if (line.split(",")[0].equals(fourthRow)) {
                    fourth.add(Integer.parseInt(line.split(",")[2]));
                    }
            }
    }

    public int getScrollSpeed(){
        return scrollSpeed;
    }
}
