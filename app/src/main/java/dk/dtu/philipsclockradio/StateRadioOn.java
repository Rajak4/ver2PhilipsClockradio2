package dk.dtu.philipsclockradio;

public class StateRadioOn extends StateAdapter {

    private boolean amOn;
    private boolean fmOn;
    private int amFreq;
    private int fmFreq;

    //Radioen starter automatisk på FM.
    @Override
    public void onEnterState(ContextClockradio context) {
        fmOn = true;
        context.ui.statusTextview.setText("FM on");
        context.ui.setDisplayText("FM:" + fmFreq);

    }

    @Override
    public void onExitState(ContextClockradio context) {

    }
    @Override
    public void onClick_Power(ContextClockradio context) {
        if(fmOn){
            fmOn = false;
            amOn = true;
            context.ui.statusTextview.setText("AM on");
            context.ui.setDisplayText("AM:" + amFreq);
        }
        else if(amOn){
            fmOn = true;
            amOn = false;
            context.ui.statusTextview.setText("FM on");
            context.ui.setDisplayText("FM:" + fmFreq);
        }

    }

    //Holdes powerknappen inde, går radioen i standby.
    @Override
    public void onLongClick_Power(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
        context.ui.statusTextview.setText("Standby");
    }
    @Override
    public void onClick_Hour(ContextClockradio context) {
        if(fmOn && fmFreq > 0){
                fmFreq--;
                context.ui.setDisplayText("FM:" + fmFreq);
        }
        else if (amOn && amFreq > 0){
            amFreq--;
            context.ui.setDisplayText("AM:" + amFreq);
        }

    }
    @Override
    public void onClick_Min(ContextClockradio context) {
        if(fmOn){
            fmFreq++;
            context.ui.setDisplayText("FM:" + fmFreq);
        }
        else {
            amFreq++;
            context.ui.setDisplayText("AM:" + amFreq);
        }

    }



}
