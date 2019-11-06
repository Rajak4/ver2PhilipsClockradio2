package dk.dtu.philipsclockradio;

public class StateSaveStation extends StateAdapter {

    private int location;

    @Override
    public void onEnterState(ContextClockradio context) {

        context.ui.turnOnTextBlink();
        context.ui.setDisplayText("ST:" + (location + 1));
        context.ui.statusTextview.setText("Save station");
    }

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

    @Override
    public void onClick_Preset(ContextClockradio context) {
      context.setState(new StateRadioOn());
    }

    @Override
    public void onClick_Power(ContextClockradio context) {

    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {

    }

    @Override
    public void onClick_AL1(ContextClockradio context) {

    }

    @Override
    public void onClick_AL2(ContextClockradio context) {

    }

    @Override
    public void onClick_Snooze(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Hour(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Min(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Sleep(ContextClockradio context) {

    }

    @Override
    public void onLongClick_AL1(ContextClockradio context) {

    }

    @Override
    public void onLongClick_AL2(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Snooze(ContextClockradio context) {

    }
}
