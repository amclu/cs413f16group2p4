package edu.luc.etl.cs313.android.Timer.model.state;

import edu.luc.etl.cs313.android.Timer.R;

class StoppedState implements TimerState {

    public StoppedState(final TimerStateView stateView) {
        this.stateView = stateView;
    }

    private final TimerStateView stateView;

    @Override
    public void onStartStop() {
        stateView.actionStart();
        if(stateView.getRuntime() != 0){
            stateView.toDecrementState();
        } else {
            stateView.actionIncrement();
            stateView.toIncrementState();
        }
    }

    @Override
    public void onTick() {
        throw new UnsupportedOperationException("onTick");
    }

    @Override
    public void updateView() {
        stateView.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.STOPPED;
    }
}
