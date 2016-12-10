package edu.luc.etl.cs313.android.Timer.model.state;


public class AlarmState implements TimerState {


    private TimerStateView sm;

    public AlarmState(TimerStateView sm) {
        this.sm = sm;
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public void onStartStop() {
        sm.timReset();
        sm.timStop();
        sm.toStoppedState();
    }

    @Override
    public void onTick() {
        sm.beep();
    }

    @Override
    public String getState() {
        return "Alarming";
    }
}
