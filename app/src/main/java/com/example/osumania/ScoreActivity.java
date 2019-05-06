package com.example.osumania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    private static final String TAG = "ScoreActivity";

    int great, ok, bad, miss, total;
    double accuracy;
    Score score;

    TextView greatScore;
    TextView okScore;
    TextView badScore;
    TextView missScore;
    TextView totalScore;
    TextView accuracyPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        score = new Score();
        setContentView(R.layout.activity_score);
        initTextViews();
        initVariables();
        populateViews();
        initOnClickListener();
    }

    private void initOnClickListener(){
        final Button toMenu = findViewById(R.id.toMenu);
        toMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMainMenu();
            }
        });
    }

    void initTextViews(){
        greatScore = findViewById(R.id.greatScore);
        okScore = findViewById(R.id.okScore);
        badScore = findViewById(R.id.badScore);
        missScore = findViewById(R.id.missScore);
        totalScore = findViewById(R.id.totalScore);
        accuracyPercentage = findViewById(R.id.accuracy);
    }

    void initVariables(){
        great = score.getTotalGreats();
        ok = score.getTotalOks();
        bad = score.getTotalBads();
        miss = score.getTotalMisses();
        total = score.getTotalNotesHit();
        accuracy = score.getAccuracy();
    }

    private void toMainMenu(){
        Log.d(TAG,"toMainMenu: Opening main menu");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    void populateViews(){
        greatScore.setText(String.valueOf(great));
        okScore.setText(String.valueOf(ok));
        badScore.setText(String.valueOf(bad));
        missScore.setText(String.valueOf(miss));
        totalScore.setText(String.valueOf(total));
        accuracyPercentage.setText(String.valueOf(accuracy));
    }

}
