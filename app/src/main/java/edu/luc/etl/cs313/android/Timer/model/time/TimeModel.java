package edu.luc.etl.cs313.android.Timer.model.time;

/**
 * The passive data model of the stopwatch.
 * It does not emit any events.
 *
 * @author laufer
 */
public interface TimeModel {
    void decRuntime();
    void setRuntime(int timeValue);
    void resetWaittime();
    void incWaittime();
    int getWaittime();
    void resetRuntime();
    void incRuntime();
    int getRunningtime();


}
