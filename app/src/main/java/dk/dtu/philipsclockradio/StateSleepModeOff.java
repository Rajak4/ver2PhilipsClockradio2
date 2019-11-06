package dk.dtu.philipsclockradio;

public class StateSleepModeOff extends StateAdapter {

    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.turnOffLED(3);
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {
        context.restartCountdown(context.getSleepTime());
        context.setState(new StateSleepModeOn());
    }
}
