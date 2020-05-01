package com.app.mp3demo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
 SeekBar seekBar;
    MediaPlayer mp;
    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         mp= MediaPlayer.create(this,R.raw.out);
         seekBar=(SeekBar)findViewById(R.id.seekBar);
            //length of audio
         seekBar.setMax(mp.getDuration());
         //TO GET THE POSITION CHANGES WHILE PLAYING AUDIO
         seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    mp.seekTo(progress);
             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         });

        updateSeekBar();
    }

    Runnable run=new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
        }
    };
    //for repeated process i.e after every 1sec seekbar position update so use handler
    // method for handler, update the seekbar position after every 1sec by calling runnable
    public void updateSeekBar(){
        //to update seekbar position and where to update
        seekBar.setProgress(mp.getCurrentPosition());
            //2 params, wait for 1sec and call the runnable
        handler.postDelayed(run,1000);


    }

    public void forward(View view) {
        mp.seekTo(mp.getCurrentPosition()+(mp.getDuration()/10));
    }

    public void play(View view) {
        mp.start();
    }

    public void stop(View view) {
        mp.stop();
        //reinitialize media player if you press stop button
        mp= MediaPlayer.create(this,R.raw.out);
    }

    public void pause(View view) {
        mp.pause();
    }

    public void back(View view) {
        mp.seekTo(mp.getCurrentPosition()-(mp.getDuration()/10));
    }
}
