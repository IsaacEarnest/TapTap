package com.example.osumania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private String song;

    private final String TAG = "MainActivity";
    Button songB1, songB2, songB3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
        initOnClickListeners();
    }
    private void initButtons(){
        songB1 = findViewById(R.id.song1);
        songB2 = findViewById(R.id.song2);
        songB3 = findViewById(R.id.song3);
    }

    private void initOnClickListeners() {
        songB1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                song = "Songs/Asu no Yozora/Asu no Yozora[Hard].osu";
                openGameActivity();
            }
        });
        songB2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                song = "Songs/Crystalia/Crystalia [Hyper].osu";
                openGameActivity();
            }
        });
        songB3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                song = "Songs/Tutorial/tutorial[4K Basics].osu";
                openGameActivity();
            }
        });

    }

    private void openGameActivity() {
        Log.d(TAG,"openGameActivity: Opening game activity"+song);
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("SONGNAME",song);
        startActivity(intent);
    }


}
