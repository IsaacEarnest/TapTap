package com.example.osumania;

import android.util.Log;
import android.widget.Chronometer;
import android.widget.ImageView;

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
    ArrayList<Node> nodes;
    private double StartTime;
    private double greatMargin,okMargin,badMargin,missMargin;
    private Chronometer chronometer;
    private ImageView up, down, left, right;

    public Game(InputStream input) throws IOException {
        this.scrollSpeed = 31;

        greatMargin = 20;
        okMargin = 40;
        badMargin = 60;
        missMargin = 150;

        first = new ArrayList<>();
        second = new ArrayList<>();
        third = new ArrayList<>();
        fourth = new ArrayList<>();
        nodes = new ArrayList<>();

        parseSongFile(input);

    }
    public void increaseScrollSpeed(){
        scrollSpeed++;
    }
    public void decreaseScrollSpeed(){
        scrollSpeed--;
    }
    public void startSong(){

        //TODO start mp3
        StartTime = System.currentTimeMillis();
    }
    public void spawnIcon(int noteHitTime, int position){
        int noteSpawnTime = noteHitTime - scrollSpeed;
        if (position == 64){
            Node node = new Node(position, 0, noteSpawnTime, left);
        }else if (position == 192){
            Node node = new Node(position, 0, noteSpawnTime, up);
        }else if (position == 320){
            Node node = new Node(position, 0, noteSpawnTime, down);
        }else if (position == 448){
            Node node = new Node(position, 0, noteSpawnTime, right);
        }
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

        double millis=System.currentTimeMillis()-StartTime;
        if(pos.equals(keys.firstK)){
            if(first!=null)
            for (Integer i:first) {
                if(Math.abs(i-millis)<greatMargin){
                    first.remove(i);
                    return "great";
                }else if(Math.abs(i-millis)<okMargin){
                    first.remove(i);
                    return "ok";
                }else if (Math.abs(i-millis)<badMargin){
                    first.remove(i);
                    return "bad";
                }else if (Math.abs(i-millis)<=missMargin){
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
                }else if (Math.abs(i-millis)<=missMargin){
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
                }else if (Math.abs(i-millis)<=missMargin){
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
                }else if (Math.abs(i-millis)<=missMargin){
                    fourth.remove(i);
                    return "miss";
                }
            }
        }
        //return "miss";
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
    public boolean isNoteAppearing(int num){
        return true;
    }

}
