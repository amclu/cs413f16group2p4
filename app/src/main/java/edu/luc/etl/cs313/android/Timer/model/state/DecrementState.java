package edu.luc.etl.cs313.android.Timer.model.state;

public class DecrementState implements TimerState {

    private TimerStateView sm;

    public DecrementState(TimerStateView sm) {
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
        sm.timeDecrease();
        if (sm.getTime() == 0) {
            sm.toAlarmState();
            return;
        }


    }

    @Override
    public String getState() {
        return "Decrementing";
    }


}
