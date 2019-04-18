package com.example.osumania;

public class Node {
    private enum Note{
        LEFT, MIDDLELEFT, MIDDLERIGHT, RIGHT
    }
    private int nodeX;
    private int nodeY;
    private int milliseconds;
    private int moveDown = 2;

    public Node(int x, int y, int milliseconds){
        this.nodeX = x;
        this.nodeY = y;
        this.milliseconds = milliseconds;
    }

    //move node down
    public void moveNodeDown(){
        nodeY += moveDown;
    }

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
