package edu.luc.etl.cs313.android.Timer.android;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import edu.luc.etl.cs313.android.Timer.test.android.AbstractTimerActivityTest;

import static org.junit.Assert.assertEquals;

public class TimerActivityTest extends ActivityInstrumentationTestCase2<TimerAdapter> {

    private AbstractTimerActivityTest actualTest;

    public TimerActivityTest() {
        super(TimerAdapter.class);
        actualTest = new AbstractTimerActivityTest() {
            @Override
            protected TimerAdapter getActivity() {
                return TimerActivityTest.this.getActivity();
            }
        };
    }
    public void testActivityScenarioSetValueTo90() throws Throwable {
        actualTest.testActivityScenarioSetValueTo90();
    }
    public void testActivityScenarioWaitingPeriod() throws Throwable {
        actualTest.testActivityScenarioWaitingPeriod();
    }

    public void testActivityScenarioMaxValueCheck() throws Throwable {
        actualTest.testActivityScenarioMaxValueCheck();
    }
}
