package com.example.osumania;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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
    MediaPlayer mp, mp2,mp3,mp4, mpSong;
    int k1,k2,k3,k4;
    Chronometer c;
    ArrayList<ImageView> notes;

    //final MediaPlayer soundEffect = MediaPlayer.create(this, );
    //TODO: find out how to access file with sound effects for the URI for MediaPLayer



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game);
            setUpComponents();

            initOnTouchListeners();
            Bundle extras = getIntent().getExtras();

            //root of the problem for finding what file to parse
            String song = extras.getString("SONGNAME");
            InputStream songInput = getAssets().open("Songs/Asu no Yozora/Asu no Yozora[Hard].osu");
            g = new Game(songInput);
            mp = MediaPlayer.create(this, R.raw.normalhitclap);
            mp2 = MediaPlayer.create(this, R.raw.normalhitclap);
            mp3 = MediaPlayer.create(this, R.raw.normalhitclap);
            mp4 = MediaPlayer.create(this, R.raw.normalhitclap);
            mpSong = MediaPlayer.create(this,R.raw.asunoyozora);
            mpSong.start();
            comboCount = 0;
            c = new Chronometer(this);
            c.setBase(SystemClock.elapsedRealtime());
            c.start();
            tickUpdate();
            k1=0;
            k2=0;
            k3=0;
            k4=0;
            notes = new ArrayList<>();
            createNote(64);
            createNote(192);
            createNote(320);
            createNote(448);
            moveNote(g.getScrollSpeed());

        } catch (IOException e) {
            Log.e("GameActivity",e.getMessage(), e);
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        mpSong.stop();
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
                    mp.start();
                    g.hit(firstK);
                    updateCombo(g.wasHit(firstK));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    left.setImageResource(R.drawable.key_left);
                    //TODO THIS
                    moveNote(g.getScrollSpeed());


                }

                return true;
            }
        });
        key2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    up.setImageResource(R.drawable.key_upd);
                    mp2.start();
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
                    mp3.start();
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
                    mp4.start();
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

    private void tickUpdate(){
        Chronometer c = new Chronometer(this);
        final double millis = c.getBase();
        c.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener()
        {
            @Override
            public void onChronometerTick(Chronometer chronometer)
            {
                if(g.isNoteAppearing(k1)){
                    createNote(64);
                    moveNote(g.getScrollSpeed());
                }
            }
        });
    }
    private void moveNote(int speed){
        for (ImageView i:notes
             ) {
            i.setY(i.getY() + speed);

        }
    }
    private void createNote(int pos){
        final ConstraintLayout cl = findViewById(R.id.cl);
        ImageView iv = new ImageView(getApplicationContext());
        ConstraintLayout.LayoutParams lp;
        switch (pos){
            case 64:
                iv.setImageDrawable(getDrawable(R.drawable.left));
                lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                iv.setLayoutParams(lp);
                cl.addView(iv);


                Log.d(TAG,"created note");
                break;
            case 192:
                iv.setImageDrawable(getDrawable(R.drawable.up));
                lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                iv.setLayoutParams(lp);
                lp.setMargins(4,0,100,100);
                cl.addView(iv);
                iv.setX(240);
                Log.d(TAG,"created note");
                break;
            case 320:
                iv.setImageDrawable(getDrawable(R.drawable.down));
                lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                iv.setLayoutParams(lp);
                cl.addView(iv);
                iv.setX(500);
                Log.d(TAG,"created note");
                break;
            case 448:
                iv.setImageDrawable(getDrawable(R.drawable.right));
                lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                iv.setLayoutParams(lp);
                cl.addView(iv);
                iv.setX(750);
                Log.d(TAG,"created note");
                break;

        }notes.add(iv);

    }


}
