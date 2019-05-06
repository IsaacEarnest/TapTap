package com.example.osumania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ScoreActivity extends AppCompatActivity {
    private static final String TAG = "ScoreActivity";

    int great, okay, bad, miss;
    double accuracy;

    ScoreActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
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

    }

    private void toMainMenu(){
        Log.d(TAG,"toMainMenu: Opening main menu");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
