package com.example.osumania;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    Button key1, key2, key3, key4;
    final MediaPlayer soundEffect = MediaPlayer.create(this, );
    //TODO: find out how to access file with sound effects for the URI for MediaPLayer
    Incrementor incrementor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setUpComponents();
        playButtonSoundEffect(key1);

    }

    private void setUpComponents(){
        key1.findViewById(R.id.key1);
        key2.findViewById(R.id.key2);
        key3.findViewById(R.id.key3);
        key4.findViewById(R.id.key4);
    }

    private void playButtonSoundEffect(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundEffect.start();
            }
        });
    }
}
