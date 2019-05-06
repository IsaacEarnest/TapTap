package com.example.osumania;

import android.os.Bundle;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class GameClassUnitTests {
    Game game;
    ArrayList<Integer> notes;
    double accuracy;

    @Before
    public void setup() throws IOException {
        InputStream input = new FileInputStream("src/main/assets/Songs/Tutorial/tutorial[4k Basics].osu");
        game = new Game(input);
        notes = new ArrayList<>();
        notes.add(1);
        accuracy = game.accuracy;

    }

    @Test
    public void calculateAccuracyTest() { //works when calcAccuracy returns a double
        double greatScore = 200.0;
        double okScore = 400.0;
        double badScore = 600.0;
        System.out.println("Unit test result = " + game.calcAccuracy(greatScore));
        assertEquals(1.0, game.calcAccuracy(greatScore));
        System.out.println("Unit test result = " + game.calcAccuracy(okScore));
        assertEquals(0.8333333333333334, game.calcAccuracy(okScore));
        System.out.println("Unit test result = " + game.calcAccuracy(badScore));
        assertEquals(0.6666666666666666, game.calcAccuracy(badScore));
    }
*/
    @Test
    public void getCurTimeMilTest(){
        System.out.println("getCurTimeMilTest = " + game.getCurTimeMil());
    }

    @Test
    public void hitMarginStringTest(){ //will fix later
        System.out.println("hitMarginStringTest = " + game.hitMarginString(notes));
        assertEquals("great", game.hitMarginString(notes));
    }
}
