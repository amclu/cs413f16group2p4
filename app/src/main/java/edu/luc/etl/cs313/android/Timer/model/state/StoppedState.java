package edu.luc.etl.cs313.android.Timer.model.state;

class StoppedState implements TimerState {

    private TimerStateView sm;

    public StoppedState(TimerStateView sm) {
        this.sm = sm;
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public void onStartStop() {
        sm.toIncrementState();
    }

    @Override
    public void onTick() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("onTick unsupported for StoppedState");
    }

    @Override
    public String getState() {
        return "Stopped";
    }
}
