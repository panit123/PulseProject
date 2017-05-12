package bottomnav.hitherejoe.com.pulse.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 1/5/2560.
 */

public class DBHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, Notification.DATABASE_NAME, null, Notification.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTIFICATION_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s INTEGER, %s TEXT, %s TEXT)",
                Notification.TABLE,
                Notification.Column.ID_NO,
                Notification.Column.BPM_NO,
                Notification.Column.DATE_NO,
                Notification.Column.RECOMMEND_NO);
///////////////////////
        String CREATE_KNOELEADGE_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s INTEGER, %s TEXT, %s TEXT, %s TEXT)",
                Knowledge.TABLE,
                Knowledge.Column.ID_NO,
                Knowledge.Column.BPM_NO,
                Knowledge.Column.SEX_NO,
                Knowledge.Column.DATE_NO,
                Knowledge.Column.RECOMMEND_NO);

        Log.i(TAG, CREATE_NOTIFICATION_TABLE);

        db.execSQL(CREATE_NOTIFICATION_TABLE);
        db.execSQL(CREATE_KNOELEADGE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_NOTIFICATION_TABLE = "DROP TABLE IF EXISTS" + Notification.TABLE;
        String DROP_KNOWLEADGE_TABLE = "DROP TABLE IF EXISTS" + Knowledge.TABLE;

        db.execSQL(DROP_KNOWLEADGE_TABLE);
        db.execSQL(DROP_NOTIFICATION_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);
    }

    public List<String> getNotificationList() {
        List<String> notification = new ArrayList<String>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query
                (Notification.TABLE, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {

            notification.add(cursor.getLong(0) + " " +
                    cursor.getLong(1) + " " +
                    cursor.getString(2) + " " +
                    cursor.getString(3));

            cursor.moveToNext();
        }

        sqLiteDatabase.close();

        return notification;
    }

    public ArrayList<Integer> getKnowledge() {
        ArrayList<Integer> knowledge = new ArrayList<Integer>();
        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query
                (Knowledge.TABLE, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        for (int i =0;i<cursor.getCount();i++)
            knowledge.add(cursor.getInt(1));
//        while (!cursor.isAfterLast()) {
//            knowledge.add(cursor.getInt(1)+"");
//        }

        return knowledge;
    }

    public void addNotification(Notification notification) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Friend.Column.ID, friend.getId());
        values.put(Notification.Column.BPM_NO, notification.getBpm());
        values.put(Notification.Column.DATE_NO, notification.getDate());
        values.put(Notification.Column.RECOMMEND_NO, notification.getRecommend());

        sqLiteDatabase.insert(Notification.TABLE, null, values);

        sqLiteDatabase.close();
    }

    public void addKnowledge(Knowledge knowledge) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Friend.Column.ID, friend.getId());
        values.put(Knowledge.Column.BPM_NO, knowledge.getBpm());
        values.put(Knowledge.Column.SEX_NO, knowledge.getSex());
        values.put(Knowledge.Column.DATE_NO, knowledge.getDate());
        values.put(Knowledge.Column.RECOMMEND_NO, knowledge.getRecommend());

        sqLiteDatabase.insert(Knowledge.TABLE, null, values);

        sqLiteDatabase.close();
    }
}
