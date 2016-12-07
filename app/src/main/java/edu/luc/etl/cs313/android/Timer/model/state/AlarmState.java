package edu.luc.etl.cs313.android.Timer.model.state;


import edu.luc.etl.cs313.android.Timer.R;

public class AlarmState implements TimerState {

    public AlarmState(final TimerStateView alarmState) {
        this.alarmState = alarmState;
    }

    private final TimerStateView alarmState;

    @Override
    public void onStartStop() {
        alarmState.actionStop();
        alarmState.actionReset();
        alarmState.toStoppedState();
    }

    @Override
    public void onTick() {
        alarmState.toAlarmState();
    }

    @Override
    public void updateView() {
        alarmState.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.ALARM;
    }
}
