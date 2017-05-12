package bottomnav.hitherejoe.com.pulse.model;

import android.provider.BaseColumns;

/**
 * Created by Acer on 3/5/2560.
 */

public class Knowledge {
    public int id;
    public long bpm;
    public String date;
    public String sex;
    public String recommend;
    public static final String DATABASE_NAME = "notification.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "knowledge";

    public Knowledge() {

    }

    public class Column {
        public static final String ID_NO = BaseColumns._ID;
        public static final String BPM_NO = "bpm_know";
        public static final String DATE_NO = "date_know";
        public static final String SEX_NO = "sex_know";
        public static final String RECOMMEND_NO = "recommend_know";
    }

    public Knowledge(int id,long bpm,String date,String sex,String recommend) {
        this.id = id;
        this.bpm = bpm;
        this.date = date;
        this.sex = sex;
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
    public String getSex() {
        return sex;
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
    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
