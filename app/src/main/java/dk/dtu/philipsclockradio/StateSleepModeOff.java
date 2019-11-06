package dk.dtu.philipsclockradio;

public class StateSleepModeOff extends StateAdapter {

    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.turnOffLED(3);
    }
}
