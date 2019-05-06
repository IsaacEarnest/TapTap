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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        initOnClickListener();
        populateTextViews();
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

    void populateTextViews(){
        Score s = Score.getInstance();
        TextView greats = findViewById(R.id.greatScore);
        TextView oks = findViewById(R.id.okScore);
        TextView bads = findViewById(R.id.badScore);
        TextView misses = findViewById(R.id.missScore);
        TextView accuracy = findViewById(R.id.accuracy);
        TextView score = findViewById(R.id.totalScore);

        greats.setText(""+s.getTotalGreats());
        oks.setText(""+s.getTotalOks());
        bads.setText(""+s.getTotalBads());
        misses.setText(""+s.getTotalMisses());
        accuracy.setText(""+s.getAccuracy()+"%");
        score.setText(""+s.getTotalScore());
    }


    private void toMainMenu(){
        Log.d(TAG,"toMainMenu: Opening main menu");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
