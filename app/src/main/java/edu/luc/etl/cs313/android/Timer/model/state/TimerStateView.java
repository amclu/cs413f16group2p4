package edu.luc.etl.cs313.android.Timer.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface TimerStateView {


    // transitions
    void toStoppedState();
    void toIncrementState();
    void toDecrementState();
    void toAlarmState();

    int getRuntime();

    // actions
    void actionInit();
    void actionReset();
    void actionResetWaittime();
    void actionRestart();
    void actionStart();
    void actionStop();
    void actionIncrement();
    void actionDecrement();
    void actionUpdateView();
    void actionWaittimeInc();
    void actionSetRuntime(int timeValue);

    // state-dependent UI updates
    void updateUIRuntime();


}
