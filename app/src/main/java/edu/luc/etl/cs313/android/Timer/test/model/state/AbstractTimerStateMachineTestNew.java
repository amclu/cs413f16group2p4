package edu.luc.etl.cs313.android.Timer.test.model.state;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.luc.etl.cs313.android.Timer.R;
import edu.luc.etl.cs313.android.Timer.model.state.TimerStateMachine;

import static org.junit.Assert.assertEquals;

abstract class AbstractTimerStateMachineTest {

    private TimerStateMachine model;

    private UnifiedMockDependency dependency;

    @Before
    public void setUp() throws Exception {
        dependency = new UnifiedMockDependency();
    }

    @After
    public void tearDown() {
        model = null;
        dependency = null;
    }

    protected void setModel(final TimerStateMachine model) {
        this.model = model;
        if (model == null)
            return;
        this.model.setUIUpdateListener(dependency);
        this.model.timInitialize();
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
        assertEquals(R.string.STOPPED, model.getState());
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
        assertEquals(R.string.DECREMENT, model.getState());
        assertEquals(99, dependency.getRunningTime());
        model.onTick();
        assertEquals(R.string.DECREMENT, model.getState());
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
