package dk.dtu.philipsclockradio;

import java.util.Date;

public class StateAlarm extends StateAdapter {


    long timeInMS;

    @Override
    public void onEnterState(ContextClockradio context) {

        //Urets klokkeslæt konverteres til MS og gemmes i variabel
        context.ui.turnOnTextBlink();
        timeInMS = context.getTime().getTime();
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffTextBlink();

    }

    @Override
    public void onClick_AL1(ContextClockradio context) {

        //Alarmens tid gemmes i Date objekt
        context.setAl1(new Date(timeInMS));
        context.ui.turnOnLED(1);
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        context.setAl2(new Date(timeInMS));
        context.ui.turnOnLED(4);
        context.setState(new StateStandby(context.getTime()));
    }

    /*De to nedenstående metoder lægger tid til den variabel som bruges til at sætte alarmen.
    Dvs. at et klik på hour lægger 360.000 MS (en time) til variablen, som ellers som udgangspunkt
    er urets klokkeslæt.
     */
    @Override
    public void onClick_Hour(ContextClockradio context) {
        timeInMS += 3600000;
        context.updateDisplayTime(new Date(timeInMS));

    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        timeInMS += 60000;
        context.updateDisplayTime(new Date(timeInMS));
    }
}
