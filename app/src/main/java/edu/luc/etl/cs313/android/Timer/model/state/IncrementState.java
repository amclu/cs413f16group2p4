package edu.luc.etl.cs313.android.Timer.model.state;

public class IncrementState implements TimerState {
    private TimerStateView sm;
    private int delay;

    public IncrementState(TimerStateView sm) {
        this.sm = sm;
        delay = 0;
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public void onStartStop() {
        delay = 0;
        sm.timeIncrease();
    }

    @Override
    public void onTick() {
        if (delay == 2) {
            delay = 0;
            sm.toRunningState();
        } else {
            delay++;
        }
    }

    @Override
    public String getState() {
        return "Increment";
    }
}
