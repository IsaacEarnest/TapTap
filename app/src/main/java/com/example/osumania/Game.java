package com.example.osumania;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    private int scrollSpeed, greatMargin, okMargin, badMargin, missMargin;
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

    public ArrayList getFirstRow(){
        return (ArrayList)first.clone();
    }

    public ArrayList getSecondRow(){
        return (ArrayList)second.clone();
    }

    public ArrayList getThirdRow(){
        return (ArrayList)third.clone();
    }

    public ArrayList getFourthRow(){
        return (ArrayList)fourth.clone();
    }

    public boolean wasHit(keys pos){
        Log.d(TAG,findHitAcc(pos));
        switch (pos) {
            case firstK:
                return !findHitAcc(pos).equals("miss");
            case secondK:
                return !findHitAcc(pos).equals("miss");
            case thirdK:
                return !findHitAcc(pos).equals("miss");
            case fourthK:
                return !findHitAcc(pos).equals("miss");
            default:
                return false;
        }
    }
    private String findHitAcc(keys pos){
        if(pos.equals(keys.firstK)){
            checkAcc(first);
        }else if(pos.equals(keys.secondK)){
            checkAcc(second);
        }else if(pos.equals(keys.thirdK)){
            checkAcc(third);
        }else if(pos.equals(keys.fourthK)){
            checkAcc(fourth);
        }
        return "test";
    }
    private String checkAcc(ArrayList<Integer> notes){
        double millis=System.currentTimeMillis()-startTime + 14*scrollSpeed +500;
        for (Integer i:notes) {
            Log.d(TAG,"time = "+(i-millis));
            if(Math.abs(i-millis)<greatMargin){
                notes.remove(i);
                return "great";
            }else if(Math.abs(i-millis)<okMargin){
                notes.remove(i);
                return "ok";
            }else if (Math.abs(i-millis)<badMargin){
                notes.remove(i);
                return "bad";
            }else if(Math.abs(i-millis)<missMargin){
                notes.remove(i);
                return "miss";
            }
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
