package edu.luc.etl.cs313.android.Timer.model.clock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An implementation of the internal clock.
 *
 * @author laufer
 */
public class DefaultClockModel implements ClockModel {

    private Timer timer;

    private OnTickListener listener;

    @Override
    public void setOnTickListener(final OnTickListener listener) {
        this.listener = listener;
    }

    @Override
    public void start() {
        timer = new Timer();


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // fire event
                listener.onTick();
            }
        }, 1000, 1000);


    }

    @Override
    public void stop() {
        try {
            timer.cancel();
        } catch (Exception e) {
        }
    }
}