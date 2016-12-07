package edu.luc.etl.cs313.android.Timer.model.time;

import static edu.luc.etl.cs313.android.Timer.common.Constants.*;

/**
 * An implementation of the default time model.
 */
public class DefaultTimeModel implements TimeModel {

    private int runningTime =0;

    private int Waittime =0;

    @Override
    public void resetRuntime() {
        runningTime = 0;
    }

    @Override
    public void incRuntime() {
        runningTime = (runningTime + SEC_PER_TICK) % SEC_PER_HOUR;
        if(runningTime>99) runningTime=99;
    }

    @Override
    public void decRuntime(){
        runningTime = (runningTime - SEC_PER_TICK) % SEC_PER_HOUR;
        if(runningTime<0) runningTime=0;
    }

    @Override
    public void setRuntime(int timeValue){
        if(timeValue <= MAX_RUN_TIME ) runningTime = timeValue;
        else timeValue = MAX_RUN_TIME;
    }


    @Override
    public int getRunningtime() {
        return runningTime;
    }

    @Override
    public int getWaittime() {return Waittime;}

    @Override
    public void resetWaittime() {Waittime=0;}

    @Override
    public void incWaittime() {Waittime++;}

}