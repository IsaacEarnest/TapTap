package com.example.osumania;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    Button button1, button2, button3, button4;
    MediaPlayer soundEffect = MediaPlayer.create(this, )


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setUpComponents();
        playButtonSoundEffect(button1, );

    }

    private void setUpComponents(){
        button1.findViewById(R.id.button1);
        button2.findViewById(R.id.button2);
        button3.findViewById(R.id.button3);
        button4.findViewById(R.id.button4);
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
