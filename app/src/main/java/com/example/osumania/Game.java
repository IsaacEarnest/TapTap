package com.example.osumania;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    private int scrollSpeed;
    enum keys{firstK, secondK, thirdK, fourthK}
    final String TAG = "GameClass";
    private double startTime;
    private Notes n;
    private Score score;
    private ArrayList<Integer> first,second,third,fourth;


    public Game(InputStream input) throws IOException {
        initVariables();
        initArrayLists();
        parseSongFile(input);
        n = new Notes(getAllNotes());
        score.newGame();
    }

    private void initVariables(){
        this.scrollSpeed = 50;
        startTime = System.currentTimeMillis();
        score = Score.getInstance();
    }

    private void initArrayLists(){
        first = new ArrayList<>();
        second = new ArrayList<>();
        third = new ArrayList<>();
        fourth = new ArrayList<>();
    }

    public int getCurTimeMil(){
        return (int)((System.currentTimeMillis())-startTime);
    }

    public ArrayList<ArrayList<Integer>> getAllNotes(){
        ArrayList<ArrayList<Integer>> allNotes = new ArrayList<>();
        allNotes.add((ArrayList)first.clone());
        allNotes.add((ArrayList)second.clone());
        allNotes.add((ArrayList)third.clone());
        allNotes.add((ArrayList)fourth.clone());
        return allNotes;
    }


    public boolean wasTest(keys pos){
        return findHitAcc(pos).equals("userTest");
    }

    public double getCurrentTime() {
        double lagCompensation = 140;
        return System.currentTimeMillis()-startTime+lagCompensation;
    }

    public boolean checkForMiss(){
        return checkForMissHelper(64)||
        checkForMissHelper(192)||
        checkForMissHelper(320)||
        checkForMissHelper(448);
    }
    private boolean checkForMissHelper(int pos){
        double lagCompensation = 700;
        if(n.hasNotes()) {
            if (getCurrentTime() - n.getCurrentNote(pos) > score.getMissMargin()+lagCompensation) {

                Log.d(TAG, "Note missed on " + pos + " row.");
                n.toNextNote(pos);
                score.onMiss();
                return true;
            }
        }
        return false;
    }

    String hitMarginString(int pos) {
        double currentTime = getCurrentTime();
//      Log.d(TAG, "User's hit = " + currentTime+", note was at "+
        //n.getCurrentNote(pos)+". System is seeing "+Math.abs(n.getCurrentNote(pos) - currentTime)+"ms difference");
        //Log.d(TAG,""+n.getFirstRow().toString());
            if (Math.abs(getCurrentTime() - n.getCurrentNote(pos)) < score.getGreatMargin()) {
            //    Log.d(TAG,"returning great");
                n.toNextNote(pos);
                score.onGreatHit();
                return "great";
            }
            if (Math.abs(getCurrentTime() - n.getCurrentNote(pos)) < score.getOkMargin()) {
             //   Log.d(TAG,"returning ok");
                n.toNextNote(pos);
                score.onOkHit();
                return "ok";
            }
            if (Math.abs(getCurrentTime() - n.getCurrentNote(pos)) < score.getBadMargin()) {
              //  Log.d(TAG,"returning bad");
                n.toNextNote(pos);
                score.onBadHit();
                return "bad";
            }
       // Log.d(TAG,"returning userTest");
        return "userTest";
    }

    private String findHitAcc(keys pos) throws NullPointerException, IllegalArgumentException{
        if (pos.equals(keys.firstK)) {
            return hitMarginString(n.getFirstPos());
        } else if (pos.equals(keys.secondK)) {
            return hitMarginString(n.getSecondPos());
        } else if (pos.equals(keys.thirdK)) {
            return hitMarginString(n.getThirdPos());
        } else if (pos.equals(keys.fourthK)) {
            return hitMarginString(n.getFourthPos());
        }
        throw new IllegalArgumentException("findHitAcc has received an invalid position.");
    }
    private void parseSongFile (InputStream input) throws IOException {
        //Log.d(TAG,"parsing");
        String line = "";
        String firstRow = "64";
        String secondRow = "192";
        String thirdRow = "320";
        String fourthRow = "448";
        int lastNoteTime = 0;
        final int getNoteTimeMillis = 2;
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        //Log.d(TAG, "File open for business!");
        //Skipping lines which aren't related to hitObjects
        while (!reader.readLine().equals("[HitObjects]")) {
        }
        //Adding Notes
        while ((line = reader.readLine()) != null) {
            if (line.split(",")[0].equals(firstRow)) {
                String noteTime = line.split(",")[getNoteTimeMillis];
                first.add(Integer.parseInt(noteTime));
                lastNoteTime = Integer.parseInt(noteTime);
            }
            if (line.split(",")[0].equals(secondRow)) {
                String noteTime = line.split(",")[getNoteTimeMillis];
                second.add(Integer.parseInt(noteTime));
                lastNoteTime = Integer.parseInt(noteTime);
            }
            if (line.split(",")[0].equals(thirdRow)) {
                String noteTime = line.split(",")[getNoteTimeMillis];
                third.add(Integer.parseInt(noteTime));
                lastNoteTime = Integer.parseInt(noteTime);
            }
            if (line.split(",")[0].equals(fourthRow)) {
                String noteTime = line.split(",")[getNoteTimeMillis];
                fourth.add(Integer.parseInt(noteTime));
                lastNoteTime = Integer.parseInt(noteTime);
            }
        }
        //lets map end a few seconds after song finishes
        //extra note does not impact gameplay, user never sees it
        addSongFinish(lastNoteTime);
    }
    private void addSongFinish(int lastNoteTime){
        int timeAfterSong = 5000;
        first.add(lastNoteTime+timeAfterSong);
        second.add(lastNoteTime+timeAfterSong);
        third.add(lastNoteTime+timeAfterSong);
        fourth.add(lastNoteTime+timeAfterSong);
    }
    public int getScrollSpeed () {
        return scrollSpeed;
    }
}

