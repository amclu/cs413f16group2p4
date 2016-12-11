package edu.luc.etl.cs313.android.Timer.test.android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import android.widget.Button;
import android.widget.EditText;
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
        getActivity().runOnUiThread(() -> assertEquals(0, getDisplayedValue()));
    }

    @Test
    public void testActivityScenarioInc() throws Throwable {
        getActivity().runOnUiThread(() -> {
            assertEquals(0, getDisplayedValue());
            assertTrue(getButton().performClick());
            performClicks(4);
        });
        Thread.sleep(5500);
        runUiThreadTasks();
        getActivity().runOnUiThread(() -> {
            assertEquals(3, getDisplayedValue());
        });
    }

    /**
     * Verifies the following:
     * time is 0.
     * Perform 5 clicks, wait 3 seconds to start and 1 second for time to decrement. Expect 4 seconds, click button,
     *  expect stopped.
     * @throws Throwable
     */

    @Test
    public void testActivityScenarioRun() throws Throwable {
        getActivity().runOnUiThread(() -> {
            assertEquals(0, getDisplayedValue());
            assertTrue(getButton().performClick());
            performClicks(4);
        });

        Thread.sleep(5500);
        runUiThreadTasks();
        getActivity().runOnUiThread(() -> {
            assertEquals(3, getDisplayedValue());
        });
    }

    protected abstract TimerAdapter getActivity();

    protected int convertTextViewToInt(final TextView t) {
        return Integer.parseInt(t.getText().toString().trim());
    }

    protected int getDisplayedValue() {
        final TextView ts = (TextView) getActivity().findViewById(R.id.seconds);
        return  Integer.parseInt(ts.getText().toString().trim());
    }

    protected Button getStartStopButton() {
        return (Button) getActivity().findViewById(R.id.startStop);
    }


    public void testActivityScenarioRunLapReset() throws Throwable{
       //did not get what it does
    }

    @Test
    public void testActivityScenarioRunToAlarmToStop() throws Throwable{
        getActivity().runOnUiThread(() -> {
            assertEquals(0, getDisplayedValue());
            getActivity().runOnUiThread(()->performClicks(6));
        });

        Thread.sleep(8500);
        getActivity().runOnUiThread(() -> {
            assertEquals(2, getDisplayedValue());
            assertTrue(getButton().performClick());
            assertEquals(0, getDisplayedValue());
        });
    }


    @Test
    public void testActivityScenarioSetValueTo90() throws Throwable{
        getActivity().runOnUiThread(() -> {
            getEditText().setText("90");
        });

        Thread.sleep(2000);
        getActivity().runOnUiThread(() -> {
            assertTrue(getButton().performClick());
            System.out.print(getEditText().getText());

        });
        Thread.sleep(8000);
        getActivity().runOnUiThread(() -> {
            assertTrue(getButton().performClick());
            assertEquals(0, getDisplayedValue());
        });
    }
    /**
     * Verifies the following:
     * time is 0.
     * Perform 6 clicks, wait 8 seconds till counter reaches 2 after decrementing ( 3 + 1 + 4 ).
     *  Expect 2 seconds, click button,
     *  expect stopped.
     * @throws Throwable
     */

    /**
     * Auxiliar methods to access the view elements
     */

    protected EditText getEditText(){
        return (EditText) getActivity().findViewById(R.id.seconds);
    }
    protected Button getButton() {
        return (Button) getActivity().findViewById(R.id.startStop);
    }

    protected void performClicks(int click){
        Button b = getButton();
        for (int i = 0; i < click; i ++){
            b.performClick();
        }
    }
    protected void setValueOfText(int value){
        EditText field = getEditText();
        field.setText(value);
    }

    protected void runUiThreadTasks() {

    }
}



