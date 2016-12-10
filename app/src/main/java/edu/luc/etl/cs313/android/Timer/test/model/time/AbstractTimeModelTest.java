package edu.luc.etl.cs313.android.Timer.test.model.time;

import org.junit.Test;

import edu.luc.etl.cs313.android.Timer.model.time.TimeModel;

import static edu.luc.etl.cs313.android.Timer.common.Constants.SEC_PER_MIN;
import static edu.luc.etl.cs313.android.Timer.common.Constants.SEC_PER_TICK;
import static org.junit.Assert.assertEquals;

public abstract class AbstractTimeModelTest {

    private TimeModel model;

    protected void setModel(final TimeModel model) {
        this.model = model;
    }


    @Test
    public void testPreconditions() {
        assertEquals(0, model.getRunningTime());
    }


    @Test
    public void testIncrementRunTimeOne() {
        final int rt = model.getRunningTime();
        model.incRuntime();
        assertEquals((rt + SEC_PER_TICK) % SEC_PER_MIN, model.getRunningTime());
    }

    @Test
    public void testIncrementRuntimeMany() {
        final int rt = model.getRunningTime();
        for (int i = 0; i < 100; i++) {
            model.incRuntime();
        }
        assertEquals(rt, model.getRunningTime());

    }

}
