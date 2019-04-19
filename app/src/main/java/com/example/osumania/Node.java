package com.example.osumania;

import android.widget.ImageView;

public class Node {
    private enum Note{
        LEFT, MIDDLELEFT, MIDDLERIGHT, RIGHT
    }
    private int nodeX;
    private int nodeY = 0;
    private int milliseconds;
    private ImageView icon;

    public Node(int x, int y, int milliseconds, ImageView icon){
        this.nodeX = x;
        this.nodeY = y;
        this.milliseconds = milliseconds;
        this.icon = icon;
    }

    //move node down
    public void moveNodeDown(){
        icon.setTranslationY(2);
    }
    public int getNodeX(){ return nodeX; }

    public Note checkNoteValue(int xValue){ //64, 192, 320, 448
        Note notePosition = Note.LEFT;
        if(xValue == 192){
            notePosition = Note.MIDDLELEFT;
        }else if(xValue == 320){
            notePosition = Note.MIDDLERIGHT;
        }else if(xValue == 448) {
            notePosition = Note.RIGHT;
        }
        return notePosition;
    }

}
