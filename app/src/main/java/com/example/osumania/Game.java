package com.example.osumania;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    private double greatMargin, okMargin, badMargin, missMargin;
    private int scrollSpeed;
    enum keys{firstK, secondK, thirdK, fourthK}
    final String TAG = "GameClass";
    private double startTime;
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
        okMargin = 400;
        badMargin = 600;
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

    private String hitMarginString(ArrayList<Integer> position) {
        double currentTime = getCurrentTime();
        for (Integer i : position) {
            Log.d(TAG, "your hit = " + currentTime+", note was at "+i+". System is seeing "+Math.abs(i - currentTime)+"ms difference");
            if (Math.abs(i - currentTime) < greatMargin) {
                Log.d(TAG,"returning great");
                position.remove(i);
                return "great";
            }
            if (Math.abs(i - currentTime) < okMargin) {
                Log.d(TAG,"returning ok");
                position.remove(i);
                return "ok";
            }
            if (Math.abs(i - currentTime) < badMargin) {
                Log.d(TAG,"returning bad");
                position.remove(i);
                return "bad";
            }
            if (Math.abs(i - currentTime) < missMargin) {
                Log.d(TAG,"returning miss");
                position.remove(i);
                return "miss";
            }
        }
        Log.d(TAG,"returning test");
        return "test";
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
        Log.d(TAG,"parsing");
        String line = "";
        String firstRow = "64";
        String secondRow = "192";
        String thirdRow = "320";
        String fourthRow = "448";
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            Log.d(TAG, "File open for business!");
            while (!reader.readLine().equals("[HitObjects]")) { }
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
