package com.example.osumania;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Handler;
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
    MediaPlayer mpSong;
    int k1,k2,k3,k4;
    ArrayList<ImageView> notes;

    String crystalia;
    String asu;
    int startTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game);
            setUpComponents();

            initOnTouchListeners();
            Bundle extras = getIntent().getExtras();

            String song = extras.getString("SONGNAME");
            InputStream songInput = getAssets().open(song);
            asu = "Songs/Asu no Yozora/Asu no Yozora[Hard].osu";
            crystalia = "Songs/Crystalia/Crystalia [Hyper].osu";
            g = new Game(songInput);
            if(song.equals(asu))
                mpSong = MediaPlayer.create(this,R.raw.asunoyozora);
            if(song.equals(crystalia))
                mpSong = MediaPlayer.create(this,R.raw.crystalia);
            mpSong.start();
            startTime = (int)System.currentTimeMillis();
            comboCount = 0;

            k1=0;
            k2=0;
            k3=0;
            k4=0;
            notes = new ArrayList<>();
            moveNote(g.getScrollSpeed());

            final ArrayList<Integer> lefts = g.getFirstRow();
            final ArrayList<Integer> ups = g.getSecondRow();
            final ArrayList<Integer> downs = g.getThirdRow();
            final ArrayList<Integer> rights = g.getFourthRow();

            final Handler handler = new Handler();
            final int delay = 10; //milliseconds
            Log.d(TAG,""+ups.get(0));

            handler.postDelayed(new Runnable(){
                public void run(){
                    //do something
                    int curTimeMil = (int)(System.currentTimeMillis())-startTime;
                    int spd = 14*g.getScrollSpeed();
                    if(lefts.size()>1||ups.size()>1||downs.size()>1||rights.size()>1)
                        handler.removeCallbacksAndMessages(null);

                    Log.d(TAG,""+curTimeMil);
                    if(lefts.get(0)<=curTimeMil+spd){
                        createNote(64);
                        lefts.remove(0);

                    }
                    if(ups.get(0)<=curTimeMil+spd){
                        createNote(192);
                        ups.remove(0);
                    }
                    if(downs.get(0)<=curTimeMil+spd){
                        createNote(320);
                        downs.remove(0);
                    }
                    if(rights.get(0)<=curTimeMil+spd){
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
                    if(g.wasHit(secondK)){
                    }
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
                    if(g.wasHit(thirdK)){
                    }
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
                    if(g.wasHit(fourthK)){
                    }
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

                break;
            case 192:
                iv.setImageDrawable(getDrawable(R.drawable.up));
                lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                iv.setLayoutParams(lp);
                lp.setMargins(4,0,100,100);
                cl.addView(iv);
                iv.setX(240);
                iv.setY(-1000);
                break;
            case 320:
                iv.setImageDrawable(getDrawable(R.drawable.down));
                lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                iv.setLayoutParams(lp);
                cl.addView(iv);
                iv.setX(500);
                iv.setY(-1000);
                break;
            case 448:
                iv.setImageDrawable(getDrawable(R.drawable.right));
                lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                iv.setLayoutParams(lp);
                cl.addView(iv);
                iv.setX(750);
                iv.setY(-1000);
                break;

        }notes.add(iv);

    }


}
