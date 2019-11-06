package dk.dtu.philipsclockradio;

public class StateSleepModeOn extends StateAdapter {

    private int[] sleepTime = {120, 90, 60, 30, 15};
    private int location = 0;

    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.turnOnLED(3);
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {

        context.ui.setDisplayText(String.valueOf(sleepTime[location++]));

    }
}
