package com.example.osumania;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Handler;
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
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;

import java.util.Timer;


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

    Timer timer;
    boolean isCounting;
    Incrementor incrementor;
    int mil;
    String crystalia;
    String asu;
    int crystaliaSpd;
    int asuSpd;

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
            InputStream songInput = getAssets().open(song);
            asu = "Songs/Asu no Yozora/Asu no Yozora[Hard].osu";
            crystalia = "Songs/Crystalia/Crystalia [Hyper].osu";
            g = new Game(songInput);
            mp = MediaPlayer.create(this, R.raw.normalhitclap);
            mp2 = MediaPlayer.create(this, R.raw.normalhitclap);
            mp3 = MediaPlayer.create(this, R.raw.normalhitclap);
            mp4 = MediaPlayer.create(this, R.raw.normalhitclap);
            if(song.equals(asu))
                mpSong = MediaPlayer.create(this,R.raw.asunoyozora);
            if(song.equals(crystalia))
                mpSong = MediaPlayer.create(this,R.raw.crystalia);
            mpSong.start();
            comboCount = 0;

            c = new Chronometer(this);
            c.setBase(SystemClock.elapsedRealtime());
            Log.d(TAG,""+c.getBase());


            c.start();
            k1=0;
            k2=0;
            k3=0;
            k4=0;
            notes = new ArrayList<>();
            moveNote(g.getScrollSpeed());
            timer = new Timer();
            incrementor = new Incrementor(timer, isCounting);
            incrementor.startTimer();
            mil=0;

            final ArrayList<Integer> lefts = g.getFirstRow();
            final ArrayList<Integer> ups = g.getSecondRow();
            final ArrayList<Integer> downs = g.getThirdRow();
            final ArrayList<Integer> rights = g.getFourthRow();
            crystaliaSpd = 18;
            asuSpd = 17;
            final double creationSpeed;
            if(song.equals(asu))
            creationSpeed = asuSpd;
            else {
                creationSpeed = crystaliaSpd;
            }

                    //17 CS to 31 SS;
            final Handler handler = new Handler();
            final int delay = 0; //milliseconds
            Log.d(TAG,""+ups.get(0));
            handler.postDelayed(new Runnable(){
                public void run(){
                    //do something
                    if(lefts.size()>1||ups.size()>1||downs.size()>1||rights.size()>1)
                        handler.removeCallbacksAndMessages(null);

                    mil++;
                    if(Math.floor(lefts.get(0)/creationSpeed)==mil){
                        createNote(64);
                        lefts.remove(0);

                    }
                    if(Math.floor(ups.get(0)/creationSpeed)==mil){
                        createNote(192);
                        ups.remove(0);
                    }
                    if(Math.floor(downs.get(0)/creationSpeed)==mil){
                        createNote(320);
                        downs.remove(0);
                    }
                    if(Math.floor(rights.get(0)/creationSpeed)==mil){
                        createNote(448);
                        rights.remove(0);
                    }
                    moveNote(g.getScrollSpeed());
                    handler.postDelayed(this, delay);
                }
            }, delay);


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
                iv.setY(-1000);



                Log.d(TAG,"created note");
                break;
            case 192:
                iv.setImageDrawable(getDrawable(R.drawable.up));
                lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                iv.setLayoutParams(lp);
                lp.setMargins(4,0,100,100);
                cl.addView(iv);
                iv.setX(240);
                iv.setY(-1000);
                Log.d(TAG,"created note");
                break;
            case 320:
                iv.setImageDrawable(getDrawable(R.drawable.down));
                lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                iv.setLayoutParams(lp);
                cl.addView(iv);
                iv.setX(500);
                iv.setY(-1000);
                Log.d(TAG,"created note");
                break;
            case 448:
                iv.setImageDrawable(getDrawable(R.drawable.right));
                lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                iv.setLayoutParams(lp);
                cl.addView(iv);
                iv.setX(750);
                iv.setY(-1000);
                Log.d(TAG,"created note");
                break;

        }notes.add(iv);

    }


}
