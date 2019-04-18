package com.example.osumania;

import android.provider.MediaStore;
import android.util.Log;
import android.widget.Chronometer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Game {
    final String TAG = "GameClass";

    private String songName;
    private int scrollSpeed;
    enum keys{
        firstK,
        secondK,
        thirdK,
        fourthK
    }
    ArrayList<Integer> first,second,third,fourth;
    private double StartTime;
    private double millis;
    private double greatMargin;
    private double okMargin;
    private double badMargin;
    private Chronometer chronometer;

    private String path;

    public Game(String path){
        this.songName = songName;
        this.scrollSpeed = 31;
        this.path = path;
        StartTime=System.currentTimeMillis();
        greatMargin = 20;
        okMargin = 40;
        badMargin = 60;
        parseSongFile();

    }
    public void increaseScrollSpeed(){
        scrollSpeed++;
    }
    public void decreaseScrollSpeed(){
        scrollSpeed++;
    }
    public void startSong(){

    }
    public void hit(keys pos){

    }
    public boolean wasHit(keys pos){
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

        millis=System.currentTimeMillis()-StartTime;
        if(pos.equals(keys.firstK)){
            if(first!=null)
            for (Integer i:first) {
                if(i-millis<greatMargin){
                    first.remove(i);
                    return "great";
                }else if(i-millis<okMargin){
                    first.remove(i);
                    return "ok";
                }else if (i-millis<badMargin){
                    first.remove(i);
                    return "bad";
                }
            }
        }else if(pos.equals(keys.secondK)){
            if(second!=null)
            for (Integer i:second) {
                if(i-millis<greatMargin){
                    second.remove(i);
                    return "great";
                }else if(i-millis<okMargin){
                    second.remove(i);
                    return "ok";
                }else if (i-millis<badMargin){
                    second.remove(i);
                    return "bad";
                }
            }
        }else if(pos.equals(keys.thirdK)){
            if(third!=null)
            for (Integer i:third) {
                if(i-millis<greatMargin){
                    third.remove(i);
                    return "great";
                }else if(i-millis<okMargin){
                    third.remove(i);
                    return "ok";
                }else if (i-millis<badMargin){
                    third.remove(i);
                    return "bad";
                }
            }
        }else if(pos.equals(keys.fourthK)){
            if(fourth!=null)
            for (Integer i:fourth) {
                if(i-millis<greatMargin){
                    fourth.remove(i);
                    return "great";
                }else if(i-millis<okMargin){
                    fourth.remove(i);
                    return "ok";
                }else if (i-millis<badMargin){
                    fourth.remove(i);
                    return "bad";
                }
            }
        }
        //return "miss";
        return "test";
    }

    public void parseSongFile(){
        Log.d(TAG,"parsing");
        BufferedReader reader;

        String line = "";
        String firstRow = "64";
        String secondRow = "192";
        String thirdRow = "320";
        String fourthRow = "448";
        try {
            //TODO have actual error handling for fileNotFound
            reader = new BufferedReader(new FileReader(path));

            while (!reader.readLine().equals("[HitObjects]")) {
            }
            //Adding Notes
            //TODO ask what path to use to get to res\songs\(specificsong)\*.osu file
            while ((line = reader.readLine()) != null) {
                if (line.split(",")[0].equals(firstRow)) {
                    first.add(Integer.parseInt(line.split(",")[2]));
                    Log.d(TAG,""+first);
                }else if (line.split(",")[3].equals(secondRow)) {
                    second.add(Integer.parseInt(line.split(",")[2]));
                    Log.d(TAG,""+second);
                }else if (line.split(",")[3].equals(thirdRow)) {
                    third.add(Integer.parseInt(line.split(",")[2]));
                    Log.d(TAG,""+third);
                }else if (line.split(",")[3].equals(fourthRow)) {
                    fourth.add(Integer.parseInt(line.split(",")[2]));
                    Log.d(TAG,""+fourth);
                    }
            }

        }catch(Exception e){
            //TODO how to pass in context to exception handling without making an unnecessary parameter for method
            //ModalDialogs.notifyException(this,e);
        }
    }


}
