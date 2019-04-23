package com.example.osumania;

import java.util.ArrayList;

public class Notes {

    private ArrayList<Integer> first, second, third, fourth;

    public Notes(ArrayList<ArrayList<Integer>> allRows){
        this.first = allRows.get(0);
        this.second = allRows.get(1);
        this.third = allRows.get(2);
        this.fourth = allRows.get(3);
    }

    public int toNextNote(int pos){
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
        return -1;
    }
    public int getCurrentNote(int pos){
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
        return -1;
    }

}
