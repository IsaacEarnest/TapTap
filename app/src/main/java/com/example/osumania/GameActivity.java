package com.example.osumania;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.osumania.Game.keys.firstK;
import static com.example.osumania.Game.keys.fourthK;
import static com.example.osumania.Game.keys.secondK;
import static com.example.osumania.Game.keys.thirdK;

public class GameActivity extends AppCompatActivity {

    final String TAG = "GameActivity";
    Button key1, key2, key3, key4;
    ImageView up,down,left,right;
    Game g;
    TextView combo;
    int comboCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setUpComponents();
        initOnTouchListeners();
        Bundle extras = getIntent().getExtras();

        //root of the problem for finding what file to parse
        String song = extras.getString("SONGNAME");
        g = new Game(song);

        comboCount = 0;

    }

    private void updateCombo(boolean wasHit){
        if(wasHit) comboCount++;
        else comboCount = 0;
        combo.setText(""+comboCount);
    }
    @SuppressLint("ClickableViewAccessibility")
    private void initOnTouchListeners(){
        key1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    left.setImageResource(R.drawable.key_leftd);
                    g.hit(firstK);
                    updateCombo(g.wasHit(firstK));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    left.setImageResource(R.drawable.key_left);
                }

                return true;
            }
        });
        key2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    up.setImageResource(R.drawable.key_upd);
                    g.hit(secondK);
                    updateCombo(g.wasHit(secondK));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    up.setImageResource(R.drawable.key_up);
                }

                return true;
            }
        });
        key3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    down.setImageResource(R.drawable.key_downd);
                    g.hit(thirdK);
                    updateCombo(g.wasHit(thirdK));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    down.setImageResource(R.drawable.key_down);
                }

                return true;
            }
        });
        key4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    right.setImageResource(R.drawable.key_rightd);
                    g.hit(fourthK);
                    updateCombo(g.wasHit(fourthK));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    right.setImageResource(R.drawable.key_right);
                }

                return true;
            }
        });
    }

    private void setUpComponents(){
        key1 = findViewById(R.id.key1);
        key2 = findViewById(R.id.key2);
        key3 = findViewById(R.id.key3);
        key4 = findViewById(R.id.key4);

        up = findViewById(R.id.up);
        down = findViewById(R.id.down);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);

        combo = findViewById(R.id.combo);

    }

    private void playButtonSoundEffect(){
        //TODO figure out how to play sound from res\raw\normalhitclap.wav
    }


}
