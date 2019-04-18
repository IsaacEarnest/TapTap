package com.example.osumania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    Button songB1, songB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songB1 = findViewById(R.id.song1);
        songB2 = findViewById(R.id.song2);
        initOnClickListeners();
    }

    private void initOnClickListeners() {
        songB1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openGameActivity();
            }
        });
    }
    private void openGameActivity() {
        String songName = "";
        Log.d(TAG,"openGameActivity: Opening game activity");
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("SONGNAME",songName);
        startActivity(intent);
    }
}
