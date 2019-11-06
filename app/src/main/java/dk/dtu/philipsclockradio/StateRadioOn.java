package dk.dtu.philipsclockradio;

public class StateRadioOn extends StateAdapter {

    //Radioen starter automatisk på FM.
    @Override
    public void onEnterState(ContextClockradio context) {
        context.setFmOn(true);
        context.ui.statusTextview.setText("FM on");
        context.ui.setDisplayText("FM:" + context.getFmFreq());

    }

    @Override
    public void onExitState(ContextClockradio context) {
    }
    @Override
    public void onClick_Power(ContextClockradio context) {
        if(context.isFmOn()){
            context.setFmOn(false);
            context.setAmOn(true);
            context.ui.statusTextview.setText("AM on");
            context.ui.setDisplayText("AM:" + context.getAmFreq());
        }
        else if(context.isAmOn()){
            context.setFmOn(true);
            context.setAmOn(false);
            context.ui.statusTextview.setText("FM on");
            context.ui.setDisplayText("FM:" + context.getFmFreq());
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
        if(context.isFmOn() && context.getFmFreq() > 0){
                context.setFmFreq(context.getFmFreq()-1);
                context.ui.setDisplayText("FM:" + context.getFmFreq());
        }
        else if (context.isAmOn() && context.getAmFreq() > 0){
            context.setAmFreq(context.getAmFreq()-1);
            context.ui.setDisplayText("AM:" + context.getAmFreq());
        }

    }
    @Override
    public void onClick_Min(ContextClockradio context) {
        if(context.isFmOn()){
            context.setFmFreq(context.getFmFreq()+1);
            context.ui.setDisplayText("FM:" + context.getFmFreq());
        }
        else {
            context.setAmFreq(context.getAmFreq()+1);
            context.ui.setDisplayText("AM:" + context.getAmFreq());
        }

    }
    //Skifter til state hvor radiokanaler kan gemmes
    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        context.setState(new StateSaveStation());
    }

}
