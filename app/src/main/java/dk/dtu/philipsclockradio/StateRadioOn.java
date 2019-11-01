package dk.dtu.philipsclockradio;

public class StateRadioOn extends StateAdapter {

    @Override
    public void onEnterState(ContextClockradio context) {

    }

    @Override
    public void onExitState(ContextClockradio context) {

    }
    @Override
    public void onClick_Power(ContextClockradio context) {

    }

    //Holdes powerknappen inde, går radioen i standby.
    @Override
    public void onLongClick_Power(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
    }



}
