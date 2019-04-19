package com.example.osumania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public Button testSongButton;
    public TextView testSongView;
    private String song;
    public Button goToTestButton;



    private final String TAG = "MainActivity";
    Button songB1, songB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toTestSoundButton();
        songB1 = findViewById(R.id.song1);
        songB2 = findViewById(R.id.song2);
        initOnClickListeners();


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
    }
    private void openGameActivity() {
        Log.d(TAG,"openGameActivity: Opening game activity"+song);
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("SONGNAME",song);
        startActivity(intent);
    }

    /*private void toTestSoundButton() {
        goToTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSoundTestActivity();
            }
        });
    }*/

    /*private void toSoundTestActivity() {
        Intent forwardIntent = new Intent(MainActivity.this, SoundTestActivity.class);
        startActivity(forwardIntent);
    }*/

}
