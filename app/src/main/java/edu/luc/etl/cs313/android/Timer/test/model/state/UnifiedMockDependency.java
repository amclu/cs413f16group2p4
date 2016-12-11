package edu.luc.etl.cs313.android.Timer.test.model.state;
import edu.luc.etl.cs313.android.Timer.model.time.TimeModel;
import edu.luc.etl.cs313.android.Timer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.Timer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.Timer.model.clock.OnTickListener;



/**
 * Created by Percy on 10/12/2016.
 */

public class UnifiedMockDependency implements TimeModel, ClockModel, TimerUIUpdateListener {

    private int timeValue = -1, stateId = -1;

    private int runTime = 0;

    private boolean started = false;
    private boolean isOnStopped = false;

    public int getTime() {
        return timeValue;
    }


    public boolean isStarted() {
        return started;
    }

    @Override
    public void beep() {
    }

    @Override
    public int updateTime(final int timeValue, final boolean isOnStopped) {
        this.timeValue = timeValue;
        this.isOnStopped = isOnStopped;
        return timeValue;
    }

    @Override
    public void setOnTickListener(OnTickListener listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void start() {
        started = true;
    }

    @Override
    public void stop() {
        started = false;
    }

    @Override
    public void resetRuntime() {
        runTime = 0;
    }

    @Override
    public void incRuntime() {
        runTime++;
    }

    @Override
    public void decRuntime() {
        runTime--;
    }

    @Override
    public int getRunningTime() {
        return runTime;
    }

    @Override
    public void setRunningTime(int runTime) {
        this.runTime = runTime;
    }
}
