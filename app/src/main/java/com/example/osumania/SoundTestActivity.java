package com.example.osumania;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class SoundTestActivity extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.test_sound_activity);

            Button one = (Button)this.findViewById(R.id.button1);
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.normalhitclap);
            one.setOnClickListener(new OnClickListener(){

                public void onClick(View v) {
                    mp.start();
                }
            });
        }
}
