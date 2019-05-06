package com.example.osumania;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ScoreActivityTest {

    int great, ok, bad, miss, total;
    double accuracy;
    TextView greatScore;
    TextView okScore;
    TextView badScore;
    TextView missScore;
    TextView totalScore;
    TextView accuracyPercent;

    @Before
    public void setUp(){
        great = 50;
        ok = 25;
        bad = 15;
        miss = 15;
        total = 100;
        accuracy = 85;
        ScoreActivity.setScore(great, greatScore);
        ScoreActivity.setScore(ok, okScore);
        ScoreActivity.setScore(bad, badScore);
        ScoreActivity.setScore(miss, missScore);
        ScoreActivity.setScore(total, totalScore);
        ScoreActivity.setAccuracy(accuracy, accuracyPercent);

    }

    @Test
    public void setScoreTest(){
        assertEquals(50, greatScore.getText());
    }

}
