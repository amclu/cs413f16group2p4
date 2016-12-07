package edu.luc.etl.cs313.android.Timer.model.clock;

/**
 * A source of onTick events for the stopwatch.
 * This interface is typically implemented by the model.
 *
 * @author laufer
 */
public interface OnTickSource {
    void setOnTickListener(OnTickListener listener);
}
