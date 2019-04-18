package com.example.osumania;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Incrementor {

    Timer timer;
    boolean isRunning;
    int interval = 50;
    ArrayList<Node> nodes;

    /*  Increments timer based on interval once started.
        Might move to MainActivity or GameActivity to
        change game properties by putting methods in run().
    */
    public void startTimer(Timer stopwatch){
        stopwatch.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Node node: nodes) {
                    node.moveNodeDown();
                }
            }
        }, 0, interval);
    }
}
