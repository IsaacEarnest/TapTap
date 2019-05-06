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
        allNotes.add(first);
        allNotes.add(second);
        allNotes.add(third);
        allNotes.add(fourth);
        return allNotes;
    }

    public boolean wasMiss(keys pos){
        return findHitAcc(pos).equals("miss");
    }

    public boolean wasTest(keys pos){
        return findHitAcc(pos).equals("test");
    }

    public double getCurrentTime() {
        double lagCompensation = 1400;
        return System.currentTimeMillis()-startTime+lagCompensation;
    }

    public void checkForMiss(){
        checkForMissHelper(64);
        checkForMissHelper(192);
        checkForMissHelper(320);
        checkForMissHelper(448);
    }
    private void checkForMissHelper(int pos){
        if(getCurrentTime()-n.getCurrentNote(pos)> score.getMissMargin()){
            n.toNextNote(pos);
            Log.d(TAG,"Note missed on "+pos+" row.");
        }
    }

    private String hitMarginString(int pos) {
        double currentTime = getCurrentTime();
            Log.d(TAG, "your hit = " + currentTime+", note was at "+n.getCurrentNote(pos)+". System is seeing "+Math.abs(n.getCurrentNote(pos) - currentTime)+"ms difference");
            if (Math.abs(n.getCurrentNote(pos) - currentTime) < score.getGreatMargin()) {
                Log.d(TAG,"returning great");
                score.onGreatHit();
                return "great";
            }
            if (Math.abs(n.getCurrentNote(pos) - currentTime) < score.getOkMargin()) {
                Log.d(TAG,"returning ok");
                score.onOkHit();
                return "ok";
            }
            if (Math.abs(n.getCurrentNote(pos) - currentTime) < score.getBadMargin()) {
                Log.d(TAG,"returning bad");
                score.onBadHit();
                return "bad";
            }
            if (Math.abs(n.getCurrentNote(pos) - currentTime) < score.getMissMargin()) {
                Log.d(TAG,"returning miss");
                score.onMiss();
                return "miss";
            }

        Log.d(TAG,"returning test");
        return "test";
    }

    private String findHitAcc(keys pos) throws NullPointerException {
        //TODO remove magic #s
        if (pos.equals(keys.firstK)) {
            return hitMarginString(64);
        } else if (pos.equals(keys.secondK)) {
            return hitMarginString(192);
        } else if (pos.equals(keys.thirdK)) {
            return hitMarginString(320);
        } else if (pos.equals(keys.fourthK)) {
            return hitMarginString(448);
        }
        return "test";
    }
            private void parseSongFile (InputStream input) throws IOException {
                Log.d(TAG,"parsing");
                String line = "";
                String firstRow = "64";
                String secondRow = "192";
                String thirdRow = "320";
                String fourthRow = "448";
                int lastNoteTime = 0;
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                Log.d(TAG, "File open for business!");
                //Skipping lines which aren't related to hitObjects
                while (!reader.readLine().equals("[HitObjects]")) {
                }
                //Adding Notes
                while ((line = reader.readLine()) != null) {
                    if (line.split(",")[0].equals(firstRow)) {
                        String noteTime = line.split(",")[2];
                        first.add(Integer.parseInt(noteTime));
                        lastNoteTime = Integer.parseInt(noteTime);
                    }
                    if (line.split(",")[0].equals(secondRow)) {
                        String noteTime = line.split(",")[2];
                        second.add(Integer.parseInt(noteTime));
                        lastNoteTime = Integer.parseInt(noteTime);
                    }
                    if (line.split(",")[0].equals(thirdRow)) {
                        String noteTime = line.split(",")[2];
                        third.add(Integer.parseInt(noteTime));
                        lastNoteTime = Integer.parseInt(noteTime);
                    }
                    if (line.split(",")[0].equals(fourthRow)) {
                        String noteTime = line.split(",")[2];
                        fourth.add(Integer.parseInt(noteTime));
                        lastNoteTime = Integer.parseInt(noteTime);
                    }
                }
                //lets map end a few seconds after song finishes
                //extra note does not impact gameplay, user never sees it
                addSongFinish(lastNoteTime);

            }
            private void addSongFinish(int lastNoteTime){
                first.add(lastNoteTime+5000);
                second.add(lastNoteTime+5000);
                third.add(lastNoteTime+5000);
                fourth.add(lastNoteTime+5000);
            }
            public int getScrollSpeed () {
                return scrollSpeed;
            }
        }

