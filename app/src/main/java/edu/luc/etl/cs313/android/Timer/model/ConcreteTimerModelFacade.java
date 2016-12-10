package edu.luc.etl.cs313.android.Timer.model;

import edu.luc.etl.cs313.android.Timer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.Timer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.Timer.model.clock.DefaultClockModel;
import edu.luc.etl.cs313.android.Timer.model.state.DefaultTimerStateMachine;
import edu.luc.etl.cs313.android.Timer.model.state.TimerStateMachine;
import edu.luc.etl.cs313.android.Timer.model.time.DefaultTimeModel;
import edu.luc.etl.cs313.android.Timer.model.time.TimeModel;

/**
 * An implementation of the model facade.
 *
 * @author laufer
 */
public class ConcreteTimerModelFacade implements TimerModelFacade {

    private TimerStateMachine stateMachine;

    private ClockModel clockModel;

    private TimeModel timeModel;

    public ConcreteTimerModelFacade() {
        timeModel = new DefaultTimeModel();
        clockModel = new DefaultClockModel();
        stateMachine = new DefaultTimerStateMachine(timeModel, clockModel);
        clockModel.setOnTickListener(stateMachine);
    }

    @Override
    public void onStart() {
        stateMachine.timInitialize();
    }

    @Override
    public void setUIUpdateListener(final TimerUIUpdateListener listener) {
        stateMachine.setUIUpdateListener(listener);
    }

    @Override
    public void onStartStop() {
        stateMachine.onStartStop();
    }
}

