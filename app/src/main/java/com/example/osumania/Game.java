package com.example.osumania;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    final String TAG = "GameClass";

    private int scrollSpeed;
    enum keys{
        firstK,
        secondK,
        thirdK,
        fourthK
    }
    ArrayList<Integer> first,second,third,fourth;
    ArrayList<Notes> notes;
    private double startTime;
    private int greatMargin,okMargin,badMargin,missMargin;


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

    public void increaseScrollSpeed(){
        scrollSpeed++;
    }
    public void decreaseScrollSpeed(){
        scrollSpeed--;
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

        double millis=System.currentTimeMillis()-startTime + 14*scrollSpeed +500;
        if(pos.equals(keys.firstK)){
            if(first!=null)
            for (Integer i:first) {
                Log.d(TAG,"time = "+(i-millis));
                if(Math.abs(i-millis)<greatMargin){
                    first.remove(i);
                    return "great";
                }else if(Math.abs(i-millis)<okMargin){
                    first.remove(i);
                    return "ok";
                }else if (Math.abs(i-millis)<badMargin){
                    first.remove(i);
                    return "bad";
                }else if(Math.abs(i-millis)<missMargin){
                    first.remove(i);
                    return "miss";
                }
            }
        }else if(pos.equals(keys.secondK)){
            if(second!=null)
            for (Integer i:second) {
                if(Math.abs(i-millis)<greatMargin){
                    second.remove(i);
                    return "great";
                }else if(Math.abs(i-millis)<okMargin){
                    second.remove(i);
                    return "ok";
                }else if (Math.abs(i-millis)<badMargin){
                    second.remove(i);
                    return "bad";
                }else if(Math.abs(i-millis)<missMargin){
                    second.remove(i);
                    return "miss";
                }
            }
        }else if(pos.equals(keys.thirdK)){
            if(third!=null)
            for (Integer i:third) {
                if(Math.abs(i-millis)<greatMargin){
                    third.remove(i);
                    return "great";
                }else if(Math.abs(i-millis)<okMargin){
                    third.remove(i);
                    return "ok";
                }else if (Math.abs(i-millis)<badMargin){
                    third.remove(i);
                    return "bad";
                }else if(Math.abs(i-millis)<missMargin){
                    third.remove(i);
                    return "miss";
                }
            }
        }else if(pos.equals(keys.fourthK)){
            if(fourth!=null)
            for (Integer i:fourth) {
                if(Math.abs(i-millis)<greatMargin){
                    fourth.remove(i);
                    return "great";
                }else if(Math.abs(i-millis)<okMargin){
                    fourth.remove(i);
                    return "ok";
                }else if (Math.abs(i-millis)<badMargin){
                    fourth.remove(i);
                    return "bad";
                }else if(Math.abs(i-millis)<missMargin){
                    fourth.remove(i);
                    return "miss";
                }
                if((i-millis)>0)
                    Log.d(TAG,"Early by "+ (i-millis));
                else Log.d(TAG,"Late by "+(i-millis));
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
            while (!reader.readLine().equals("[HitObjects]")) {

            }
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
