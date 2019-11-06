package dk.dtu.philipsclockradio;

import android.os.Handler;

import java.util.Calendar;
import java.util.Date;



public class ContextClockradio {
    private State currentState;
    private Date mTime;
    private String mDisplayText;
    public boolean isClockRunning = false;
    private int amFreq;
    private int fmFreq;
    private boolean amOn;
    private boolean fmOn;
    private int[] savedAm = new int[20];
    private int[] savedFm = new int[20];
    private final int SLEEPTIME = 5000;

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            setState(new StateStandby(mTime));
        }
    };
    public static Handler mHandler = new Handler();


    public int getAmFreq(){
        return amFreq;
    }
    public int getFmFreq(){
        return fmFreq;
    }


    public static MainUI ui;

    public ContextClockradio(MainUI context){
        ui = context;

        //Sætter tiden til 12.00, hvis tiden ikke er sat endnu
        if(mTime == null){
            Calendar date = Calendar.getInstance();
            date.set(2019, 1, 1, 12, 00);
            mTime = date.getTime();
        }

        //Når app'en starter, så går vi ind i Standby State
        currentState = new StateStandby(mTime);
        currentState.onEnterState(this);
    }

    public void startCountdown(int time){
        mHandler.postDelayed(mRunnable, time);
    }
    public void restartCountdown(int time){
        mHandler.postDelayed(mRunnable, time);
        mHandler.removeCallbacks(mRunnable);
    }

    //setState er når vi skifter State
    void setState(final State newState) {
        currentState.onExitState(this);
        currentState = newState;
        currentState.onEnterState(this);
        System.out.println("Current state: "+ newState.getClass().getSimpleName());
    }

    //Opdaterer kontekst time state og UI
    void setTime(Date time){
        mTime = time;
        if(currentState.getClass().getSimpleName().equals("StateStandby")){
            updateDisplayTime();
        }
    }


    void updateDisplayTime(){
        mDisplayText = mTime.toString().substring(11,16);
        ui.setDisplayText(mDisplayText);
    }

    public Date getTime(){
        return mTime;
    }


    //Disse metoder bliver kaldt fra UI tråden
    public void onClick_Hour() {
        currentState.onClick_Hour(this);
    }

    public void onClick_Min() {
        currentState.onClick_Min(this);
    }

    public void onClick_Preset() {
        currentState.onClick_Preset(this);
    }

    public void onClick_Power() {
        currentState.onClick_Power(this);
    }

    public void onClick_Sleep() {
        currentState.onClick_Sleep(this);
    }

    public void onClick_AL1() {
        currentState.onClick_AL1(this);
    }

    public void onClick_AL2() {
        currentState.onClick_AL2(this);
    }

    public void onClick_Snooze() {
        currentState.onClick_Snooze(this);
    }

    public void onLongClick_Hour(){
        currentState.onLongClick_Hour(this);
    }

    public void onLongClick_Min(){
        currentState.onLongClick_Min(this);
    }

    public void onLongClick_Preset(){
        currentState.onLongClick_Preset(this);
    }

    public void onLongClick_Power(){
        currentState.onLongClick_Power(this);
    }

    public void onLongClick_Sleep(){
        currentState.onLongClick_Sleep(this);
    }

    public void onLongClick_AL1(){
        currentState.onLongClick_AL1(this);
    }

    public void onLongClick_AL2(){
        currentState.onLongClick_AL2(this);
    }

    public void onLongClick_Snooze(){
        currentState.onLongClick_Snooze(this);
    }

    public void setFmFreq(int fmFreq) {
        this.fmFreq = fmFreq;
    }

    public void setAmFreq(int amFreq) {
        this.amFreq = amFreq;
    }

    public int[] getSavedFm() {
        return savedFm;
    }

    public void setSavedFm(int location, int value) {
        this.savedFm[location] = value;
    }

    public int[] getSavedAm() {
        return savedAm;
    }

    public void setSavedAm(int location, int value) {
        this.savedAm[location] = value;
    }

    public boolean isAmOn() {
        return amOn;
    }

    public void setAmOn(boolean amOn) {
        this.amOn = amOn;
    }

    public boolean isFmOn() {
        return fmOn;
    }

    public void setFmOn(boolean fmOn) {
        this.fmOn = fmOn;
    }

    public int getSleepTime() {
        return SLEEPTIME;
    }
}