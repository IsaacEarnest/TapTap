package com.example.osumania;

import android.util.Log;

import java.util.ArrayList;

public class Notes {
    private final static String TAG = "NotesClass";

    private ArrayList<Integer> first, second, third, fourth;

    private static Notes single_instance = null;

    private Notes(ArrayList<ArrayList<Integer>> allRows){
        this.first = allRows.get(0);
        this.second = allRows.get(1);
        this.third = allRows.get(2);
        this.fourth = allRows.get(3);
    }


    public static Notes getInstance(ArrayList<ArrayList<Integer>> allRows)
    {
        if (single_instance == null)
            single_instance = new Notes(allRows);

        return single_instance;
    }
    public int toNextNote(int pos)throws IllegalArgumentException{
        switch (pos) {
            case 64:
                return first.remove(0);
            case 192:
                return second.remove(0);
            case 320:
                return third.remove(0);
            case 448:
                return fourth.remove(0);
        }
        throw new IllegalArgumentException("toNextNote has received an invalid position.");
    }
    public int getCurrentNote(int pos)throws IllegalArgumentException{
        switch (pos) {
            case 64:
                return first.get(0);
            case 192:
                return second.get(0);
            case 320:
                return third.get(0);
            case 448:
                return fourth.get(0);
        }
        throw new IllegalArgumentException("getCurrentNote has received an invalid position.");
    }
    public boolean hasNotes(){
        return !(first.isEmpty()||second.isEmpty()||third.isEmpty()||fourth.isEmpty());
    }

}
