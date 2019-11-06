package dk.dtu.philipsclockradio;

public class StateSleepMode extends StateAdapter {
    @Override
    public void onEnterState(ContextClockradio context) {
        context.startCountdown(context.getSleepTime());
        context.setState(new StateSleepModeOn());
    }

}
