package edu.luc.etl.cs313.android.Timer.model.state;

import edu.luc.etl.cs313.android.Timer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.Timer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.Timer.model.time.TimeModel;

import static edu.luc.etl.cs313.android.Timer.common.Constants.MAX_RUN_TIME;
import static edu.luc.etl.cs313.android.Timer.common.Constants.MAX_WAIT_TIME;

/**
 * An implementation of the state machine for the stopwatch.
 *
 * @author laufer
 */
public class DefaultTimerStateMachine implements TimerStateMachine {

    public DefaultTimerStateMachine(final TimeModel timeModel, final ClockModel clockModel) {
        this.timeModel = timeModel;
        this.clockModel = clockModel;
    }

    private final TimeModel timeModel;

    private final ClockModel clockModel;

    /**
     * The internal state of this adapter component. Required for the State pattern.
     */
    private TimerState state;

    protected void setState(final TimerState state) {
        this.state = state;
        uiUpdateListener.updateState(state.getId());
    }

    private TimerUIUpdateListener uiUpdateListener;

    @Override
    public void setUIUpdateListener(final TimerUIUpdateListener uiUpdateListener) {
        this.uiUpdateListener = uiUpdateListener;
    }

    // forward event uiUpdateListener methods to the current state
    // these must be synchronized because events can come from the
    // UI thread or the timer thread
    @Override public synchronized void onStartStop() { state.onStartStop(); }
    @Override public synchronized void onTick()      { state.onTick(); }

    @Override public void updateUIRuntime() { uiUpdateListener.updateTime(timeModel.getRunningtime()); }


    // known states
    private final TimerState STOPPED     = new StoppedState(this);
    private final TimerState INCREMENT     = new IncrementState(this);
    private final TimerState DECREMENT = new DecrementState(this);
    private final TimerState ALARM = new AlarmState(this);

    // transitions
    @Override public void toStoppedState()    { setState(STOPPED); }
    @Override public void toIncrementState()    { setState(INCREMENT); }
    @Override public void toDecrementState()    { setState(DECREMENT); }
    @Override public void toAlarmState()    { setState(ALARM); }


    // actions
    @Override public void actionInit()       { toStoppedState(); actionReset(); }
    @Override public void actionReset()      { timeModel.resetRuntime(); timeModel.resetWaittime(); actionUpdateView(); }
    @Override public void actionResetWaittime() {timeModel.resetWaittime();}
    @Override public void actionStart()      { clockModel.start(); }
    @Override public void actionStop()       { clockModel.stop(); }
    @Override public void actionRestart(){clockModel.stop(); clockModel.start();}

    @Override public void actionUpdateView() { state.updateView(); }

    public int getRuntime()                  { return timeModel.getRunningtime(); }
    public void actionSetRuntime(int timeValue){ timeModel.setRuntime(timeValue); actionUpdateView(); }

    @Override public void actionIncrement()
    {
        timeModel.incRuntime();
        actionUpdateView();
        if(timeModel.getRunningtime()==MAX_RUN_TIME)
        {
                toDecrementState();
        }
        else toIncrementState();
    }

    @Override public void actionWaittimeInc(){
        timeModel.incWaittime();
        if(timeModel.getWaittime()==MAX_WAIT_TIME) {
            toDecrementState();

        }
    }
    @Override public void actionDecrement()
    {
        timeModel.decRuntime();
        actionUpdateView();
        if (timeModel.getRunningtime()==0)
        {
            toAlarmState();

        }
        else toDecrementState();
    }
}
