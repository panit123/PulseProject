package bottomnav.hitherejoe.com.pulse;

/**
 * Created by Acer on 25/4/2560.
 */

public class Bpm {
    public int Signal;
    public String Time;

    public Bpm(){

    }

    public Bpm(int Signal,String Time) {
/*Blank default constructor essential for Firebase*/
        this.Signal = Signal;
        this.Time = Time;
    }

    public String getTime() {
        return Time;
    }
    public void setTime(String Time) {
        this.Time = Time;
    }
    public int getSignal() {
        return Signal;
    }
    public void setSignal(int Signal) {
        this.Signal = Signal;
    }
}
