package bottomnav.hitherejoe.com.pulse.model;

import android.provider.BaseColumns;

/**
 * Created by Acer on 1/5/2560.
 */

public class Notification {
    public int id;
    public long bpm;
    public String date;
    public String recommend;
    public static final String DATABASE_NAME = "notification.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "notification";

    public Notification() {

    }

    public class Column {
        public static final String ID_NO = BaseColumns._ID;
        public static final String BPM_NO = "bpm_no";
        public static final String DATE_NO = "date_no";
        public static final String RECOMMEND_NO = "recommend_no";
    }

    public Notification(int id,long bpm,String date,String recommend) {
        this.id = id;
        this.bpm = bpm;
        this.date = date;
        this.recommend = recommend;
    }
    public  int getId(){
        return id;
    }
    public long getBpm() {
        return bpm;
    }
    public String getDate() {
        return date;
    }
    public String getRecommend() {
        return recommend;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setBpm(long bpm) {
        this.bpm = bpm;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
