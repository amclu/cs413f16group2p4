package edu.luc.etl.cs313.android.Timer.common;

/**
 * A listener for UI update notifications.
 * This interface is typically implemented by the adapter, with the
 * notifications coming from the model.
 *
 * @author laufer
 */
public interface TimerUIUpdateListener {
    int updateTime(int time);
    void updateState(int stateId);
    void beep();
}
