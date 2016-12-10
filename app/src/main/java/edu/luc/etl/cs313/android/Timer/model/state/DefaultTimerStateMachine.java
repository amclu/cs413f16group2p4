package edu.luc.etl.cs313.android.Timer.model.state;

import edu.luc.etl.cs313.android.Timer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.Timer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.Timer.model.time.TimeModel;

/**
 * An implementation of the state machine for the stopwatch.
 *
 * @author laufer
 */
public class DefaultTimerStateMachine implements TimerStateMachine {

    private TimerState state;
    private TimerState runningState = new DecrementState(this);
    private TimerState stoppedState = new StoppedState(this);
    private TimerState incrementState = new IncrementState(this);
    private TimerState alarmState = new AlarmState(this);
    private ClockModel clock;
    private TimeModel time;
    private TimerUIUpdateListener UIUpdateListener;

    public DefaultTimerStateMachine(TimeModel t, ClockModel c) {
        this.clock = c;
        this.time = t;
    }

    public String getState() {
        return this.state.getState();
    }

    public void setState(TimerState state) {
        this.state = state;
    }

    //Updates to the time
    @Override
    public int getTime() {
        return this.time.getRunningTime();
    }

    //Transitions to the time
    @Override
    public void toRunningState() {
        clock.stop();
        beep();
        this.setState(this.runningState);
        clock.start();

    }

    @Override
    public synchronized void toStoppedState() {
        this.clock.stop();
        this.setState(this.stoppedState);
    }

    @Override
    public synchronized void toIncrementState() {
        this.setState(this.incrementState);
    }

    @Override
    public synchronized void toAlarmState() {
        this.setState(this.alarmState);
    }

    //Actions done to the time like start stop increase and decrease depending on inputs
    @Override
    public synchronized void timInitialize() {
        toStoppedState();
        timReset();
    }

    @Override
    public synchronized void timReset() {
        time.resetRuntime();
        actionUpdateView();
    }

    @Override
    public synchronized void timStart() {
        clock.start();
    }

    @Override
    public synchronized void timStop() {
        clock.stop();
    }

    @Override
    public synchronized void timeIncrease() {
        if (time.getRunningTime() < 99) {
            timStop();
            time.incRuntime();
            actionUpdateView();
            if (time.getRunningTime() == 99) {
                toRunningState();
                return;
            }
            clock.start();
        }
    }

    @Override
    public synchronized void timeDecrease() {

        time.decRuntime();
        actionUpdateView();


    }

    @Override
    public synchronized void actionUpdateView() {
        state.updateView();
    }

    @Override
    public void beep() {
        UIUpdateListener.beep();
    }

    @Override
    public synchronized void updateUIRuntime() {
        int i = 0;

        int ep = UIUpdateListener.updateTime(time.getRunningTime());
        if (time.getRunningTime() < 1) {
            while (i < ep) {

                time.incRuntime();
                i++;

            }
        }
    }

    @Override
    public synchronized void onStartStop() {
        state.onStartStop();
    }

    @Override
    public void setUIUpdateListener(TimerUIUpdateListener listener) {
        UIUpdateListener = listener;
    }

    @Override
    public synchronized void onTick() {
        this.state.onTick();
    }
}
