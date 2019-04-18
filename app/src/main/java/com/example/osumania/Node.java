package com.example.osumania;

public class Node {

    private int nodeX;
    private int nodeY;
    private int moveDown = 2;

    public Node(int x, int y){
        this.nodeX = x;
        this.nodeY = y;
    }

    //move node down
    public void moveNodeDown(){
        nodeY += moveDown;
    }


}
