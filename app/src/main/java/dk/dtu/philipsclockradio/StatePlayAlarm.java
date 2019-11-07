package dk.dtu.philipsclockradio;

public class StatePlayAlarm extends StateAdapter {
    private int alarm;

    public StatePlayAlarm(int alarm){
        this.alarm = alarm;
    }

    @Override
    public void onEnterState(ContextClockradio context) {

        //Hvis alarm 1 er muted men 2 ikke er muted
        if(context.isMuted1() && !context.isMuted2()){

            //Updating display text to show which alarm is ringing depending on what's coming with the constructor
            String ringRing = "*AL" + alarm + "*";

            context.ui.setDisplayText(ringRing);
            if(context.ui.getDisplayLed4()){
                context.ui.statusTextview.setText("Alarm 2 ringing via radio");

            }
            else if(context.ui.getDisplayLed5()){
                context.ui.statusTextview.setText("Alarm 2 ringing via alarm");
            }

        }
        //Hvis alarm 2 er muted men alarm 1 ikke er muted
        if(!context.isMuted1() && context.isMuted2()){

            //Updating display text to show which alarm is ringing depending on what's coming with the constructor
            String ringRing = "*AL" + alarm + "*";

            context.ui.setDisplayText(ringRing);
            if(context.ui.getDisplayLed1()){
                context.ui.statusTextview.setText("Alarm 1 ringing via radio");
            }
            else if(context.ui.getDisplayLed2()){
                context.ui.statusTextview.setText("Alarm 1 ringing via alarm");
            }
        }
        if(!context.isMuted1() && !context.isMuted2()){
            //Updating display text to show which alarm is ringing depending on what's coming with the constructor
            String ringRing = "*AL" + alarm + "*";
            context.ui.setDisplayText(ringRing);
            if(context.ui.getDisplayLed4()){
                context.ui.statusTextview.setText("Alarm 2 ringing via radio");

            }
            else if(context.ui.getDisplayLed5()){
                context.ui.statusTextview.setText("Alarm 2 ringing via alarm");
            }
            else if(context.ui.getDisplayLed1()){
                context.ui.statusTextview.setText("Alarm 1 ringing via radio");
            }
            else if(context.ui.getDisplayLed2()){
                context.ui.statusTextview.setText("Alarm 1 ringing via alarm");
            }

        }
        //Hvis begge alarmer er muted, går uret tilbage til standby state
        if(context.isMuted1() && context.isMuted2()){
            context.setState(new StateStandby(context.getTime()));
        }


    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.statusTextview.setText("");
    }

    @Override
    public void onClick_AL1(ContextClockradio context) {
        if(alarm == 1){
            context.setState(new StateStandby(context.getTime()));
        }
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        if(alarm == 2){
            context.setState(new StateStandby(context.getTime()));
        }
    }
}
