package dk.dtu.philipsclockradio;

public class StateSaveStation extends StateAdapter {

    private int location;

    @Override
    public void onEnterState(ContextClockradio context) {

        context.ui.turnOnTextBlink();
        context.ui.setDisplayText("ST:" + (location + 1));
        context.ui.statusTextview.setText("Save station");
    }

    //Gemmer radiokanaler i array på exit
    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffTextBlink();
        if (context.isAmOn()) {
            context.setSavedAm(location, context.getAmFreq());
        } else {
          context.setSavedFm(location, context.getFmFreq());
        }

    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        if(location > 1) {
            --location;
            context.ui.setDisplayText("ST:" + location);
        }
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        if(location < 20) {
            ++location;
            context.ui.setDisplayText("ST:" + location);
        }
    }

    //Tænder radioen
    @Override
    public void onClick_Preset(ContextClockradio context) {
      context.setState(new StateRadioOn());
    }
}
