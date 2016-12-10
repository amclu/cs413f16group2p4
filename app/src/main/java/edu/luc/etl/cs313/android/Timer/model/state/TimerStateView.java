package edu.luc.etl.cs313.android.Timer.model.state;

interface TimerStateView {
    // updates the time
    int getTime();

    // transitions the states of the time
    void toRunningState();  //4 possible states

    void toStoppedState();

    void toIncrementState();

    void toAlarmState();

    // actions done by the stopwatch
    void timInitialize();

    void timReset();

    void timStart();

    void timStop();

    void timeIncrease();

    void timeDecrease();

    void actionUpdateView();

    void beep();

    // Updates the UI depending on the state
    void updateUIRuntime();

}
