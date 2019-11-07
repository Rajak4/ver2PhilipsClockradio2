package dk.dtu.philipsclockradio;

public class StateSleepModeOff extends StateAdapter {

    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.turnOffLED(3);
        context.ui.setDisplayText("Off");
    }

    //Nulstiller idle timeren, så den starter forfra ved 5 sek
    @Override
    public void onClick_Sleep(ContextClockradio context) {
        context.restartCountdown(context.getSleepTime());
        context.setState(new StateSleepModeOn());
    }
}
