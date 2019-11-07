package dk.dtu.philipsclockradio;

public class StateSleepMode extends StateAdapter {

    //Idle timer
    @Override
    public void onEnterState(ContextClockradio context) {
        context.startCountdown(context.getSleepTime());
        context.setState(new StateSleepModeOn());
    }

}
