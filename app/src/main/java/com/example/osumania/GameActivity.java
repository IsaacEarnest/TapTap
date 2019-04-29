package com.example.osumania;

import android.annotation.SuppressLint;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;
import java.util.ArrayList;

import static com.example.osumania.Game.keys.firstK;
import static com.example.osumania.Game.keys.fourthK;
import static com.example.osumania.Game.keys.secondK;
import static com.example.osumania.Game.keys.thirdK;

public class GameActivity extends AppCompatActivity {

    private final String TAG = "GameActivity";
    private Button key1, key2, key3, key4;
    private ImageView up,down,left,right;
    private TextView combo;

    private Game g;
    private Notes n;

    private  MediaPlayer mpSong;

    private ArrayList<ImageView> notes;
    private ArrayList<Integer> lefts, ups, downs, rights;
    private String crystalia, tutorial, asu;
    private int comboCount;
    private int startTime;
    private int first,second,third,fourth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game);
            setUpComponents();
            initOnTouchListeners();
            startSong();

            startTime = (int)System.currentTimeMillis();
            comboCount = 0;

            initKeysComponents();
            initNotes();
            initHandler();
        } catch (Exception e) {
            Log.e("GameActivity",e.getMessage(), e);
        }
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
    private void initNotes(){
        ArrayList<ArrayList<Integer>> allNotes = new ArrayList<>();
        allNotes.add(g.getFirstRow());
        allNotes.add(g.getSecondRow());
        allNotes.add(g.getThirdRow());
        allNotes.add(g.getFourthRow());
        n = new Notes(allNotes);
    }
    private void startSong()throws Exception{
        Bundle extras = getIntent().getExtras();

        String song = extras.getString("SONGNAME");
        InputStream songInput = getAssets().open(song);
        asu = "Songs/Asu no Yozora/Asu no Yozora[Hard].osu";
        crystalia = "Songs/Crystalia/Crystalia [Hyper].osu";
        tutorial = "Songs/Tutorial/tutorial[4K Basics].osu";
        g = new Game(songInput);
        if(song.equals(asu))
            mpSong = MediaPlayer.create(this,R.raw.asunoyozora);
        if(song.equals(crystalia))
            mpSong = MediaPlayer.create(this,R.raw.crystalia);
        if(song.equals(tutorial))
            mpSong = MediaPlayer.create(this,R.raw.tutorial);
        mpSong.start();
    }
    private void initHandler(){
        final Handler handler = new Handler();
        final int delay = 1; //milliseconds

        handler.postDelayed(new Runnable(){
            public void run(){
                int curTimeMil = (int)(System.currentTimeMillis())-startTime;
                int spd = 14*g.getScrollSpeed();
                if(!(lefts.isEmpty()&&ups.isEmpty()&&downs.isEmpty()&&rights.isEmpty())) {

                    if (n.getCurrentNote(first) <= curTimeMil + spd) {
                        createNote(first);
                        n.toNextNote(first);
                    }
                    if (n.getCurrentNote(second) <= curTimeMil + spd) {
                        createNote(second);
                        n.toNextNote(second);
                    }
                    if (n.getCurrentNote(third) <= curTimeMil + spd) {
                        createNote(third);
                        n.toNextNote(third);
                    }
                    if (n.getCurrentNote(fourth) <= curTimeMil + spd) {
                        createNote(fourth);
                        n.toNextNote(fourth);
                    }
                    moveNote(g.getScrollSpeed());
                }

                handler.postDelayed(this, delay);
            }
        }, delay);
    }
    private void initKeysComponents(){
        first=64;
        second=192;
        third=320;
        fourth=448;
        notes = new ArrayList<>();

        lefts = g.getFirstRow();
        ups = g.getSecondRow();
        downs = g.getThirdRow();
        rights = g.getFourthRow();
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
        initOnTouchListener(key1);
        initOnTouchListener(key2);
        initOnTouchListener(key3);
        initOnTouchListener(key4);
    }
    @SuppressLint("ClickableViewAccessibility")
    private void initOnTouchListener(Button key){
        final ImageView UIPos;
        final int holdKey;
        final Game.keys keyPos;
        final int openKey;
        if(key.equals(key1)){
            UIPos = left;
            holdKey = R.drawable.key_leftd;
            keyPos = firstK;
            openKey = R.drawable.key_left;
        }else if (key.equals(key2)){
            UIPos = up;
            holdKey = R.drawable.key_upd;
            keyPos = secondK;
            openKey = R.drawable.key_up;
        }else if (key.equals(key3)){
            UIPos = down;
            holdKey = R.drawable.key_downd;
            keyPos = thirdK;
            openKey = R.drawable.key_down;
        }else{
            UIPos = right;
            holdKey = R.drawable.key_rightd;
            keyPos = fourthK;
            openKey = R.drawable.key_right;
        }
        key.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    UIPos.setImageResource(holdKey);

                    updateCombo(g.wasHit(keyPos));
                    if(g.wasHit(keyPos)){
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    UIPos.setImageResource(openKey);
                }

                return true;
            }
        });
    }




    private void moveNote(int speed){
        int offscreen = 3000;
        for (ImageView i:notes) {
            if(i.getY()<offscreen){
            i.setY(i.getY() + speed);
            }
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
