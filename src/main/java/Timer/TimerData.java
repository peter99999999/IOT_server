package Timer;



/**
 * Created by EDE67167 on 2017-08-15.
 */

public class TimerData {
    String onTime;
    String offTime;
    Boolean timerOn;

    public Boolean getTimerOn() {
        return timerOn;
    }

    public void setTimerOn(Boolean timerOn) {
        this.timerOn = timerOn;
    }

    public String getOnTime() {
        return onTime;
    }

    public void setOnTime(String onTime) {
        this.onTime = onTime;
    }

    public String getOffTime() {
        return offTime;
    }

    public void setOffTime(String offTime) {
        this.offTime = offTime;
    }
}
