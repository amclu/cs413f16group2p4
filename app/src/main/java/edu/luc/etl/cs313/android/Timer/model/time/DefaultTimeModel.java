package edu.luc.etl.cs313.android.Timer.model.time;

/**
 * An implementation of the default time model.
 */
public class DefaultTimeModel implements TimeModel {
    private int runTime = 0;


    @Override
    public void resetRuntime() {
        this.runTime = 0;
    }

    @Override
    public void incRuntime() {
        this.runTime += 1;
    }

    @Override
    public void decRuntime() {
        this.runTime -= 1;
    }

    @Override
    public int getRunningTime() {
        return this.runTime;
    }

    @Override
    public void setRunningTime(int runTime) {
        this.runTime = runTime;
    }

}