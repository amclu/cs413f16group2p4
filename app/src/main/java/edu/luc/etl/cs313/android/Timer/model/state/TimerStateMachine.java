package edu.luc.etl.cs313.android.Timer.model.state;

import edu.luc.etl.cs313.android.Timer.common.TimerUIUpdateSource;
import edu.luc.etl.cs313.android.Timer.common.TimerUIListener;
import edu.luc.etl.cs313.android.Timer.model.clock.OnTickListener;

/**
 * The state machine for the state-based dynamic model of the stopwatch.
 * This interface is part of the State pattern.
 *
 * @author laufer
 */
public interface TimerStateMachine extends TimerUIListener, OnTickListener, TimerUIUpdateSource, TimerStateView { }
