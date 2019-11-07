package dk.dtu.philipsclockradio;

public class StatePlayAlarm extends StateAdapter {
    private boolean AL1;

    public StatePlayAlarm(boolean AL1){
        this.AL1 = AL1;
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        String ringRing = AL1 ? "*AL1*" : "*AL2*";
        context.ui.setDisplayText(ringRing);
        if(context.ui.getDisplayLed1()){
            context.ui.statusTextview.setText("Alarm 1 ringing via radio");
        }
        else if(context.ui.getDisplayLed2()){
            context.ui.statusTextview.setText("Alarm 1 ringing via alarm");
        }
        else if(context.ui.getDisplayLed4()){
            context.ui.statusTextview.setText("Alarm 2 ringing via radio");
        }
        else if(context.ui.getDisplayLed5()){
            context.ui.statusTextview.setText("Alarm 2 ringing via alarm");
        }
    }

    @Override
    public void onExitState(ContextClockradio context) {

    }

    @Override
    public void onClick_AL1(ContextClockradio context) {
        if(AL1){
            context.setState(new StateStandby(context.getTime()));
        }
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        if(!AL1){
            context.setState(new StateStandby(context.getTime()));
        }
    }
}
