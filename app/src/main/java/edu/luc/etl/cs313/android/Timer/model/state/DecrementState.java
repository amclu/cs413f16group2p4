package edu.luc.etl.cs313.android.Timer.model.state;

import edu.luc.etl.cs313.android.Timer.R;

public class DecrementState implements TimerState {

    public DecrementState(final TimerStateView decState){
        this.decState=decState;
    }

    private final TimerStateView decState;

    @Override
    public void onStartStop() {
        decState.actionStop();
        decState.actionReset();
        decState.toStoppedState();
    }

    @Override
    public void onTick()
    {
        decState.actionDecrement();
    }

    @Override
    public void updateView() {
        decState.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.DECREMENT;
    }

}
