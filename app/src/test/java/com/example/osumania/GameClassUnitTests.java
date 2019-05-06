package com.example.osumania;

import android.os.Bundle;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;

public class GameClassUnitTests {
    Game game;
    Score score;
    ArrayList<Integer> notes;
    double accuracy;

    @Before
    public void setup() throws IOException {
        InputStream input = new FileInputStream("src/main/assets/Songs/Tutorial/tutorial[4K Basics].osu");
        game = new Game(input);
        score = new Score();
        notes = new ArrayList<>();
        notes.add(1);
        accuracy = score.accuracy;
    }

    @Test
    public void calculateAccuracyTest() {
        score.updateAccuracy(score.getGreatScore());
        System.out.println(score.getAccuracy());
        assertEquals(100., score.getAccuracy()); //when totalGreats = 1 and totalNotesHit = 1
        //works when more notes are added
    }

    @Test
    public void getTotalMissesTest(){
        assertEquals(0, score.getTotalMisses());
    }

    @Test
    public void hitMarginStringTest(){
        System.out.println(game.getCurrentTime());
        assertEquals("ok", game.hitMarginString(192));
    }
/*
    @Test
    public void hitMarginStringTest(){ //will fix later
        System.out.println("hitMarginStringTest = " + game.hitMarginString(notes));
        assertEquals("great", game.hitMarginString(notes));
    }
    */
}
