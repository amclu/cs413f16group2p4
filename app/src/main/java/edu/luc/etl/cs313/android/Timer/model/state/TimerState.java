package edu.luc.etl.cs313.android.Timer.model.state;

import edu.luc.etl.cs313.android.Timer.common.TimerUIListener;
import edu.luc.etl.cs313.android.Timer.model.clock.OnTickListener;

public interface TimerState extends TimerUIListener, OnTickListener {
    void updateView();

    String getState();
}
