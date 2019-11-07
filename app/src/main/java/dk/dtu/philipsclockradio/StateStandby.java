package dk.dtu.philipsclockradio;

import android.os.Handler;
import java.util.Date;

public class StateStandby extends StateAdapter {

    private Date mTime;
    private static Handler mHandler = new Handler();
    private ContextClockradio mContext;
    private int localLedCounter1;
    private int localLedCounter2;


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

                switch (mContext.checkAlarms(mContext.getAl1(), mContext.getAl2(), mContext)){
                    case 0: break;
                    case 1: mContext.setState(new StatePlayAlarm(1));
                            break;
                    case 2: mContext.setState(new StatePlayAlarm(2));
                            break;
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
        this.localLedCounter1 = context.getLedCounter1();
        this.localLedCounter2 = context.getLedCounter2();

        context.updateDisplayTime();
        if(!context.isClockRunning){
            startClock();
        }
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.setLedCounter1(localLedCounter1);
        context.setLedCounter2(localLedCounter2);
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
       localLedCounter1 = localLedCounter1 + 1 % 3;

       if(context.getAl1() != null){
           switch (localLedCounter1){
               case 0: context.ui.turnOnLED(1);
                        context.ui.turnOffLED(2);
                        break;
               case 1: context.ui.turnOnLED(2);
                        context.ui.turnOffLED(1);
                        break;
               case 2: context.ui.turnOffLED(1);
                        context.ui.turnOffLED(2);
                        break;
           }
       }
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        if(context.getAl2() != null){
            switch (localLedCounter2){
                case 0: context.ui.turnOnLED(4);
                    context.ui.turnOffLED(5);
                    break;
                case 1: context.ui.turnOnLED(5);
                    context.ui.turnOffLED(4);
                    break;
                case 2: context.ui.turnOffLED(4);
                    context.ui.turnOffLED(5);
                    break;
            }
        }
    }
}
