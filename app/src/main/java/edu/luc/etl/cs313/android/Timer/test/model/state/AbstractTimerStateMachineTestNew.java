package edu.luc.etl.cs313.android.Timer.test.model.state;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.luc.etl.cs313.android.Timer.R;
import edu.luc.etl.cs313.android.Timer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.Timer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.Timer.model.clock.OnTickListener;
import edu.luc.etl.cs313.android.Timer.model.state.TimerStateMachine;
import edu.luc.etl.cs313.android.Timer.model.time.TimeModel;

import static org.junit.Assert.assertEquals;

/**
 * Testcase superclass for the stopwatch state machine model. Unit-tests the state
 * machine in fast-forward mode by directly triggering successive tick events
 * without the presence of a pseudo-real-time clock. Uses a single unified mock
 * object for all dependencies of the state machine model.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */
abstract class AbstractTimerStateMachineTest {

    private TimerStateMachine model;

    private UnifiedMockDependency dependency;

    @Before
    public void setUp() throws Exception {
        dependency = new UnifiedMockDependency() {
            @Override
            public int getRunningtime() {
                return 0;
            }
        };
    }

    @After
    public void tearDown() {
        dependency = null;
    }

    /**
     * Setter for dependency injection. Usually invoked by concrete testcase
     * subclass.
     *
     * @param model
     */
    protected void setModel(final TimerStateMachine model) {
        this.model = model;
        if (model == null)
            return;
        this.model.setUIUpdateListener(dependency);
        this.model.actionInit();
    }

    protected UnifiedMockDependency getDependency() {
        return dependency;
    }

    /**
     * Verifies that we're initially in the stopped state (and told the listener
     * about it).
     */
    @Test
    public void testPreconditions() {
        assertEquals(R.string.STOPPED, dependency.getState());
    }


    /**
     * Verifies the following scenario:
     * starting time is 0,clicks till max=99 times, state should be decrement,
     * time value should be 99, expect 1 beep, wait time 1 seconds, expected time vale = 98
     */

    @Test
    public void testScenarioIncUntilFull() {
        assertEquals(0, dependency.getRunningTime());
        for (int i = 1; i <= 99; i++)
            model.onStartStop();
        assertEquals(R.string.DECREMENT, dependency.getState());
        assertEquals(99, dependency.getRunningTime());
        model.onTick();
        assertEquals(R.string.DECREMENT, dependency.getState());
        assertEquals(98, dependency.getRunningTime());
    }

    /**
 * Sends the given number of tick events to the model.
 *
 * @param n the number of tick events
 */
protected void onTickRepeat(final int n) {
    for (int i = 0; i < n; i++)
        model.onTick();
}

    /**
     * Checks whether the model has invoked the expected time-keeping
     * methods on the mock object.
     */
    protected void assertTimeEquals(final int t) {
        assertEquals(t, dependency.getTime());
    }
}

/**
 * Manually implemented mock object that unifies the three dependencies of the
 * stopwatch state machine model. The three dependencies correspond to the three
 * interfaces this mock object implements.
 *
 * @author laufer
 */
abstract class UnifiedMockDependency implements TimeModel,ClockModel, TimerUIUpdateListener
{

    private int timeValue = -1, stateId = -1;

    private int runningTime = 0, WaitTime = 0;

    private boolean started = false;

    public int getTime() {
        return timeValue;
    }

    public int getState() {
        return stateId;
    }

    public boolean isStarted() {
        return started;
    }

    public int getRunningTime(){
        return runningTime;
    }
    @Override
    public void updateTime(final int timeValue) {
        this.timeValue = timeValue;
    }

    @Override
    public void updateState(final int stateId) {
        this.stateId = stateId;
    }

    @Override
    public void setOnTickListener(OnTickListener listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void start() {
        started = true;
    }

    @Override
    public void stop() {
        started = false;
    }

    @Override
    public void resetRuntime() {
        runningTime = 0;
    }

    @Override
    public void incRuntime() {
        runningTime++;
    }

    public void decRuntime(){runningTime--;}

    @Override
    public void setRuntime(int timeValue) {
        runningTime = timeValue;
    }

   public void incWaittime(){WaitTime++;}

    public void resetWaittime(){WaitTime=0;}

    public int getWaittime(){return WaitTime;}

}
