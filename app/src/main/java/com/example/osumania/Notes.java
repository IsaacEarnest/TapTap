package com.example.osumania;

import java.util.ArrayList;

public class Notes {
    private final static String TAG = "NotesClass";

    private ArrayList<Integer> first, second, third, fourth;
    private final int firstPos = 64;
    private final int secondPos = 192;
    private final int thirdPos = 320;
    private final int fourthPos = 448;

    public Notes(ArrayList<ArrayList<Integer>> allRows){
        this.first = allRows.get(0);
        this.second = allRows.get(1);
        this.third = allRows.get(2);
        this.fourth = allRows.get(3);

    }

    public int toNextNote(int pos)throws IllegalArgumentException{
        switch (pos) {
            case firstPos:
                return first.remove(0);
            case secondPos:
                return second.remove(0);
            case thirdPos:
                return third.remove(0);
            case fourthPos:
                return fourth.remove(0);
        }
        throw new IllegalArgumentException("toNextNote has received an invalid position.");
    }

    public int getCurrentNote(int pos)throws IllegalArgumentException{
        switch (pos) {
            case firstPos:
                return first.get(0);
            case secondPos:
                return second.get(0);
            case thirdPos:
                return third.get(0);
            case fourthPos:
                return fourth.get(0);
        }
        throw new IllegalArgumentException("getCurrentNote has received an invalid position.");
    }

    public boolean hasNotes(){
        return !(first.isEmpty() || second.isEmpty() || third.isEmpty() || fourth.isEmpty());
    }

}
