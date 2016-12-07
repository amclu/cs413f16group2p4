package edu.luc.etl.cs313.android.Timer.model;

import edu.luc.etl.cs313.android.Timer.common.TimerUIUpdateSource;
import edu.luc.etl.cs313.android.Timer.common.TimerUIListener;


/**
 * A thin model facade. Following the Facade pattern,
 * this isolates the complexity of the model from its clients (usually the adapter).
 *
 * @author laufer
 */
public interface TimerModelFacade extends TimerUIListener, TimerUIUpdateSource {
    void onStart();
}
