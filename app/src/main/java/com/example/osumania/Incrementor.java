package com.example.osumania;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Incrementor {

    private Timer timer;
    private boolean isRunning;
    ArrayList<Notes> notes;
    private Notes note;

    public Incrementor(Timer timer, boolean isRunning){
        this.timer = timer;
        this.isRunning = isRunning;
    }

    /*  Increments timer based on interval once started.
        Might move to MainActivity or GameActivity to
        change game properties by putting methods in run().
    */
    public void startTimer(){
        isRunning = true;
        int interval = 50;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            }
        }, 0, interval);
    }
    /*  Spawn node based on next song note's millisecond value, may spawn certain
        millisecond value earlier so user can see note's occurrence before the user
        has to press the corresponding button to the note.
    */


}
