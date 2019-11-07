package dk.dtu.philipsclockradio;

import android.os.Handler;
import java.util.Date;

public class StateStandby extends StateAdapter {

    private Date mTime;
    private static Handler mHandler = new Handler();
    private ContextClockradio mContext;

    StateStandby(Date time){
        mTime = time;
    }

    //Opdaterer hvert 60. sekund med + 1 min til tiden
    Runnable mSetTime = new Runnable() {

        @Override
        public void run() {
            try {
                long currentTime = mTime.getTime();
                mTime.setTime(currentTime + 60000);
                mContext.setTime(mTime);

                if(mContext.checkAlarms(mContext.getAl1(), mContext.getAl2(), mContext)){
                    mContext.setState(new StatePlayAlarm());
                }

            } finally {
                mHandler.postDelayed(mSetTime, 60000);
            }
        }
    };

    void startClock() {
        mSetTime.run();
        mContext.isClockRunning = true;
    }

    void stopClock() {
        mHandler.removeCallbacks(mSetTime);
        mContext.isClockRunning = false;
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        //Lokal context oprettet for at Runnable kan få adgang
        mContext = context;

        context.updateDisplayTime();
        if(!context.isClockRunning){
            startClock();
        }
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        stopClock();
        context.setState(new StateSetTime());
    }
    @Override
    public void onLongClick_Power(ContextClockradio context) {
        stopClock();
        context.setState(new StateRadioOn());
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {
        context.setState(new StateSleepMode());
    }

    @Override
    public void onLongClick_AL1(ContextClockradio context) {
        context.setState(new StateAlarm());
    }

    @Override
    public void onLongClick_AL2(ContextClockradio context) {
        context.setState(new StateAlarm());
    }

    @Override
    public void onClick_AL1(ContextClockradio context) {
        if (context.getAl1() != null) {
            if (context.isAl1RadioLightOn()) {
                context.ui.turnOnLED(1);
                context.ui.turnOffLED(2);
                context.setAl1RadioLightOn(false);
            } else {
                context.ui.turnOffLED(1);
                context.ui.turnOnLED(2);
                context.setAl1RadioLightOn(true);
            }
        }
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        if (context.getAl2() != null) {
            if (context.isAl2RadioLightOn()) {
                context.ui.turnOnLED(4);
                context.ui.turnOffLED(5);
                context.setAl2RadioLightOn(false);
            } else {
                context.ui.turnOffLED(4);
                context.ui.turnOnLED(5);
                context.setAl2RadioLightOn(true);
            }
        }
    }
}
