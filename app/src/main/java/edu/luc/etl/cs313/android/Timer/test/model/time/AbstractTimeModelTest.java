package edu.luc.etl.cs313.android.Timer.test.model.time;

import static edu.luc.etl.cs313.android.Timer.common.Constants.SEC_PER_MIN;
import static edu.luc.etl.cs313.android.Timer.common.Constants.SEC_PER_TICK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.luc.etl.cs313.android.Timer.model.time.TimeModel;

/**
 * Testcase superclass for the time model abstraction.
 * This is a simple unit test of an object without dependencies.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */
public abstract class AbstractTimeModelTest {

    private TimeModel model;

    /**
     * Setter for dependency injection. Usually invoked by concrete testcase
     * subclass.
     *
     * @param model
     */
    protected void setModel(final TimeModel model) {
        this.model = model;
    }

    /**
     * Verifies that runtime and laptime are initially 0 or less.
     */
    @Test
    public void testPreconditions() {
        assertEquals(0, model.getRunningtime());
    }

    /**
     * Verifies that runtime is incremented correctly.
     */
    @Test
    public void testIncrementRuntimeOne() {
        final int rt = model.getRunningtime();
        model.incRuntime();
        assertEquals((rt + SEC_PER_TICK) % SEC_PER_MIN, model.getRunningtime());
    }

    /**
     * Verifies that runtime turns over correctly.
     */
    @Test
    public void testIncrementRuntimeMany() {
        final int rt = model.getRunningtime();
        for (int i = 0; i < 100; i ++) {
            model.incRuntime();
        }
        assertEquals(rt, model.getRunningtime());

    }

   @Test
    public void testWaitTime(){
        model.setRuntime(0);
        for(int i=1;i<=5;i++)
            model.incRuntime();
        assertEquals(5, model.getRunningtime());
        assertEquals(0, model.getWaittime());
        for(int i=1;i<=2;i++)
            model.incWaittime();
        assertEquals(5, model.getRunningtime());
        assertEquals(2,model.getWaittime());
    }

}
