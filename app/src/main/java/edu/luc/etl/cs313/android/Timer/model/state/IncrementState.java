package edu.luc.etl.cs313.android.Timer.model.state;

import edu.luc.etl.cs313.android.Timer.R;

public class IncrementState implements TimerState
{
    public IncrementState(final TimerStateView incState){
        this.incState=incState;
    }

    private final TimerStateView incState;

    @Override
    public void onStartStop() {
        incState.actionRestart();
        incState.actionResetWaittime();
        incState.actionIncrement();
    }

    @Override
    public void onTick() {
        incState.actionWaittimeInc();
    }

    @Override
    public void updateView() {
        incState.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.INCREMENT;
    }
}
