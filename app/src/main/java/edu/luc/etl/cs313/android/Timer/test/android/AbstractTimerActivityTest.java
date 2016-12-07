package edu.luc.etl.cs313.android.Timer.test.android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

import edu.luc.etl.cs313.android.Timer.R;
import edu.luc.etl.cs313.android.Timer.android.TimerAdapter;

import static edu.luc.etl.cs313.android.Timer.common.Constants.SEC_PER_MIN;

/**
 * Abstract GUI-level test superclass of several essential stopwatch scenarios.
 *
 * @author laufer
 *
 * TODO move this and the other tests to src/test once Android Studio supports
 * non-instrumentation unit tests properly.
 */
public abstract class AbstractTimerActivityTest {

    /**
     * Verifies that the activity under test can be launched.
     */
    @Test
    public void testActivityCheckTestCaseSetUpProperly() {
        assertNotNull("activity should be launched successfully", getActivity());
    }

    /**
     * Verifies the following scenario: time is 0.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioInit() throws Throwable {
        getActivity().runOnUiThread((new Runnable() {
            @Override
            public void run() {
                assertEquals(0, getDisplayedValue());
            }
        }));

    }

    @Test
    public void testActivityScenarioInc() throws Throwable {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assertEquals(0, getDisplayedValue());
                for (int i = 0; i < 9; i++) {
                    assertTrue(getStartStopButton().performClick());
                }
            }
        });
        Thread.sleep(2500);
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assertEquals(9, getDisplayedValue());
            }
        });
    }

    @Test
    public void testActivityScenarioRun() throws Throwable {
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            assertEquals(0, getDisplayedValue());
            for (int i = 0; i < 9; i++) {
                assertTrue(getStartStopButton().performClick());
            }
        }});
        Thread.sleep(2500); // <-- do not run this in the UI thread!
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assertEquals(9, getDisplayedValue());
                for (int i = 0; i < 8; i++) {
                    assertTrue(getStartStopButton().performClick());
                }
            }
        });
        Thread.sleep(1000); // <-- do not run this in the UI thread!
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assertEquals(9, getDisplayedValue());
            }
        });
        Thread.sleep(2500);
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assertEquals(9, getDisplayedValue());
            }
        });
        Thread.sleep(1000);
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assertEquals(8, getDisplayedValue());
            }
        });
        Thread.sleep(6000);
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            assertEquals(2, getDisplayedValue());
        }});
    }

    protected abstract TimerAdapter getActivity();

    protected int convertTextViewToInt(final TextView t) {
        return Integer.parseInt(t.getText().toString().trim());
    }

    protected int getDisplayedValue() {
        final TextView ts = (TextView) getActivity().findViewById(R.id.seconds);
        return  convertTextViewToInt(ts);
    }

    protected Button getStartStopButton() {
        return (Button) getActivity().findViewById(R.id.startStop);
    }

    protected void runUiThreadTasks() {

    }
}



