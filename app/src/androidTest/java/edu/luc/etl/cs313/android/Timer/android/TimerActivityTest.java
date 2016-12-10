package edu.luc.etl.cs313.android.Timer.android;

import android.test.ActivityInstrumentationTestCase2;

import edu.luc.etl.cs313.android.Timer.test.android.AbstractTimerActivityTest;


/**
 * Concrete Android test subclass. Has to inherit from framework class
 * and uses delegation to concrete subclass of abstract test superclass.
 * IMPORTANT: project must export JUnit 4 to make it available on the
 * device.
 *
 * @author laufer
 * @see http://developer.android.com/tools/testing/activity_testing.html
 */
public class TimerActivityTest extends ActivityInstrumentationTestCase2<TimerAdapter> {

    /**
     * Creates an {@link ActivityInstrumentationTestCase2} for the
     * {@link SkeletonActivity} activity.
     */
    public TimerActivityTest() {
        super(TimerAdapter.class);
        actualTest = new AbstractTimerActivityTest() {
            @Override
            protected TimerAdapter getActivity() {
                // return activity instance provided by instrumentation test
                return TimerActivityTest.this.getActivity();
            }
        };
    }

    private AbstractTimerActivityTest actualTest;

    public void testActivityCheckTestCaseSetUpProperly() {
        actualTest.testActivityCheckTestCaseSetUpProperly();
    }

    public void testActivityScenarioRun() throws Throwable {
        actualTest.testActivityScenarioRun();
    }

    public void testActivityScenarioRunLapReset() throws Throwable {
        actualTest.testActivityScenarioRunLapReset();
    }

    public void testActivityScenarioRunToAlarmToStop() throws Throwable{
        actualTest.testActivityScenarioRunToAlarmToStop();
    }

    /*Test to do
    2.	testInitiallyAtMin – the click_count value is initially at its minimum (0).
3.	testActivityTestCaseSetUpProperty – Activity launched OK.
4.	testIncrement – one is added to the initial as well as displayed click_count value.
5.	testIncrementRuntimeOne – times are correct after one second.
6.	testIncrementRuntimeMany – times are correct after one min 33 seconds.
7.	testActivityScenarioInc
     */
}
