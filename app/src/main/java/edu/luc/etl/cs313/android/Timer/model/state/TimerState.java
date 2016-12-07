package edu.luc.etl.cs313.android.Timer.model.state;

import edu.luc.etl.cs313.android.Timer.common.TimerUIListener;
import edu.luc.etl.cs313.android.Timer.model.clock.OnTickListener;

/**
 * A state in a state machine. This interface is part of the State pattern.
 *
 * @author laufer
 */
interface TimerState extends TimerUIListener, OnTickListener {
    void updateView();
    int getId();
}
