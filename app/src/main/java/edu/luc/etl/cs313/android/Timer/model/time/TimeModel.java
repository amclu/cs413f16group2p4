package edu.luc.etl.cs313.android.Timer.model.time;

public interface TimeModel {


    void resetRuntime();
    void incRuntime();
    void decRuntime();
    int getRunningTime();
    void setRunningTime(int runTime);
}

