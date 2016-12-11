package edu.luc.etl.cs313.android.Timer.test.android;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Test;

import edu.luc.etl.cs313.android.Timer.R;
import edu.luc.etl.cs313.android.Timer.android.TimerAdapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Abstract GUI-level test superclass of several essential stopwatch scenarios.
 *
 * @author laufer
 *         <p>
 *         TODO move this and the other tests to src/test once Android Studio supports
 *         non-instrumentation unit tests properly.
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
        getActivity().runOnUiThread(() -> assertEquals(0, getDisplayedValue()));
    }

    protected abstract TimerAdapter getActivity();

    protected int convertTextViewToInt(final TextView t) {
        return Integer.parseInt(t.getText().toString().trim());
    }

    protected int getDisplayedValue() {
        final TextView ts = (TextView) getActivity().findViewById(R.id.seconds);
        return Integer.parseInt(ts.getText().toString().trim());
    }

    protected Button getStartStopButton() {
        return (Button) getActivity().findViewById(R.id.startStop);
    }

    @Test
    public void testActivityScenarioSetValueTo90() throws Throwable {
        getActivity().runOnUiThread(() -> {
            getEditText().setText("90");
        });

        Thread.sleep(1000);
        getActivity().runOnUiThread(() -> {
            getActivity().runOnUiThread(() -> performClicks(9));
            assertEquals(99, getDisplayedValue());

        });
        Thread.sleep(8500);
        getActivity().runOnUiThread(() -> {
            assertTrue(getButton().performClick());
            assertEquals(0, getDisplayedValue());
        });
    }

    /**
     * Verifies the timer waits for 3 seconds to start dec:
     * time is 0.
     * Perform 4 clicks, wait 2.5 seconds, expect display value 4.
     *
     * @throws Throwable
     */

    @Test
    public void testActivityScenarioWaitingPeriod() throws Throwable {
        getActivity().runOnUiThread(() -> {
            assertEquals(0, getDisplayedValue());
            getActivity().runOnUiThread(() -> performClicks(4));
        });

        Thread.sleep(2500);
        runUiThreadTasks();
        getActivity().runOnUiThread(() -> {
            assertEquals(4, getDisplayedValue());
        });
    }

    /**
     * Verifies the timer has max value = 99:
     * time is 0.
     * Perform 99 clicks, click again once, display value remains 99,
     * wait 4.5 seconds, perform click 1,
     * changed to stopped state and value is 0.
     *
     * @throws Throwable
     */

    @Test
    public void testActivityScenarioMaxValueCheck() throws Throwable {

        getActivity().runOnUiThread(() -> {
            assertEquals(0, getDisplayedValue());
            getEditText().setText("99");
        });
        Thread.sleep(2000);
        getActivity().runOnUiThread(() -> {
            assertTrue(getButton().performClick());
            assertEquals(99, getDisplayedValue());
        });



        Thread.sleep(4500);
        getActivity().runOnUiThread(() -> {
            getActivity().runOnUiThread(() -> performClicks(1));
            assertEquals(0, getDisplayedValue());
        });
    }


    /**
     * Auxiliar methods to access the view elements
     */

    protected EditText getEditText() {
        return (EditText) getActivity().findViewById(R.id.seconds);
    }

    protected Button getButton() {
        return (Button) getActivity().findViewById(R.id.startStop);
    }

    protected void performClicks(int click) {
        Button b = getButton();
        for (int i = 0; i < click; i++) {
            b.performClick();
        }
    }

    protected void setValueOfText(int value) {
        EditText field = getEditText();
        field.setText(value);
    }

    protected void runUiThreadTasks() {

    }
}



