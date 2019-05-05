package com.example.osumania;

import android.os.Bundle;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static junit.framework.TestCase.assertTrue;

public class GameClassUnitTests {
    Game game;

    @Before
    public void setup() throws IOException {
        InputStream input = new FileInputStream("src/main/assets/Songs/Tutorial/tutorial[4k Basics].osu");
        game = new Game(input);
    }

    @Test
    public void test1() {
        assertTrue(true);
    }
}
