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
    private Date al1;
    private Date al2;
    private boolean al1RadioLightOn;
    private boolean al2RadioLightOn;
    private int ledCounter1 = 0;
    private int ledCounter2 = 0;
    private boolean muted1 = false;
    private boolean muted2 = false;


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


    /**
     *
     * @param al1 er første date objekt med alarm1
     * @param al2 er anden date objekt med alarm2
     * @param context
     * @return 1 hvis første alarm skal ringe, 2 hvis anden alarm skal ringe, 0 hvis ingen alarmer skal ringe.
     */
    public int checkAlarms(Date al1, Date al2, ContextClockradio context){

        //Gemmer en string med urets klokkeslæt
        String time = context.getTime().toString().substring(11,16);

        //Tjekker om en alarm er tom eller ej
        if(al1 != null){

            //Sammenligner alarmens tid med urets klokkeslæt
            if(al1.toString().substring(11,16).equals(time)){
                return 1;
            }
        }
        if(al2 != null){
            if(al2.toString().substring(11,16).equals(time)){
                return 2;
            }
        }
        return 0;
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

    //Kører runnable efter x tid (bruges fx til at gå tilbage til standby mode hvis man er idle)
    public void startCountdown(int time){
        mHandler.postDelayed(mRunnable, time);
    }
    public void restartCountdown(int time){
        mHandler.removeCallbacks(mRunnable);
        mHandler.postDelayed(mRunnable, time);
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

    //Overload for at give parameter med som kan bruges i stedet for mTime
    void updateDisplayTime(Date time){
        mDisplayText = time.toString().substring(11, 16);
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

    public Date getAl1() {
        return al1;
    }

    public void setAl1(Date al1) {
        this.al1 = al1;
    }

    public Date getAl2() {
        return al2;
    }

    public void setAl2(Date al2) {
        this.al2 = al2;
    }


    public boolean isAl1RadioLightOn() {
        return al1RadioLightOn;
    }

    public void setAl1RadioLightOn(boolean al1RadioLightOn) {
        this.al1RadioLightOn = al1RadioLightOn;
    }

    public boolean isAl2RadioLightOn() {
        return al2RadioLightOn;
    }

    public void setAl2RadioLightOn(boolean al2RadioLightOn) {
        this.al2RadioLightOn = al2RadioLightOn;
    }

    public int getLedCounter1() {
        return ledCounter1;
    }

    public void setLedCounter1(int ledCounter) {
        this.ledCounter1 = ledCounter;
    }

    public int getLedCounter2() {
        return ledCounter2;
    }

    public void setLedCounter2(int ledCounter2) {
        this.ledCounter2 = ledCounter2;
    }

    public boolean isMuted1() {
        return muted1;
    }

    public void setMuted1(boolean muted) {
        this.muted1 = muted;
    }

    public boolean isMuted2() {
        return muted2;
    }

    public void setMuted2(boolean muted2) {
        this.muted2 = muted2;
    }
}