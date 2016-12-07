package edu.luc.etl.cs313.android.Timer.model.clock;

/**
 * The active model of the internal clock that periodically emits tick events.
 *
 * @author laufer
 */
public interface ClockModel extends OnTickSource {
    void start();
    void stop();
}
