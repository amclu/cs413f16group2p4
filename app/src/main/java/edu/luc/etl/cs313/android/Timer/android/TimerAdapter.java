package edu.luc.etl.cs313.android.Timer.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import edu.luc.etl.cs313.android.Timer.R;
import edu.luc.etl.cs313.android.Timer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.Timer.model.ConcreteTimerModelFacade;
import edu.luc.etl.cs313.android.Timer.model.TimerModelFacade;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import java.io.IOException;

public class TimerAdapter extends Activity implements TimerUIUpdateListener {
    TimerModelFacade timerFacade ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerFacade = new ConcreteTimerModelFacade();
        timerFacade.setUIUpdateListener(this);


    }
    @Override
    public void onStart() {
        super.onStart();
        timerFacade.onStart();
    }

    /**
     * Updates the UI time.
     *
     * @parameter time in seconds
     */
    @Override
    public int updateTime(int time, boolean isOnStopped) {
        // Adapter used to schedule incoming UI events.
        final EditText timeInSeconds = (EditText) findViewById(R.id.seconds);
        int currentTime = Integer.parseInt(timeInSeconds.getText().toString());
        if(time != -1) {
            runOnUiThread(() -> {
                //enabling or disabling Text View
                final int seconds = time;
                timeInSeconds.setFocusable(isOnStopped);
                timeInSeconds.setFocusableInTouchMode(isOnStopped);
                timeInSeconds.setText(Integer.toString(seconds / 10) + Integer.toString(seconds % 10));
            });
        }else{
            runOnUiThread(() -> {
                //enabling or disabling Text View
                final int seconds = currentTime;
                timeInSeconds.setFocusable(isOnStopped);
                timeInSeconds.setFocusableInTouchMode(isOnStopped);
                timeInSeconds.setText(Integer.toString(seconds / 10) + Integer.toString(seconds % 10));
            });
        }
        if(time == -1) {
            return currentTime;
        }
        return 0;
    }

    public void onStartStop(View view){
        this.timerFacade.onStartStop();
    }

    /**
     * When timer reaches 0 it plays a noise.
     */
    public void beep() {
        final Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final MediaPlayer mediaPlayer = new MediaPlayer();
        final Context context = getApplicationContext();

        try {
            mediaPlayer.setDataSource(context, defaultRingtoneUri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
            mediaPlayer.start();
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}


