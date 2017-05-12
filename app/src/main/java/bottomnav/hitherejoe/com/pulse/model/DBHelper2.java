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
 * Created by Acer on 4/5/2560.
 */

public class DBHelper2 extends SQLiteOpenHelper {
    private final String TAG = getClass().getSimpleName();

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper2(Context context) {
        super(context, Knowledge.DATABASE_NAME, null, Knowledge.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_KNOELEADGE_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s INTEGER, %s TEXT, %s TEXT, %s TEXT)",
                Knowledge.TABLE,
                Knowledge.Column.ID_NO,
                Knowledge.Column.BPM_NO,
                Knowledge.Column.SEX_NO,
                Knowledge.Column.DATE_NO,
                Knowledge.Column.RECOMMEND_NO);
        Log.i(TAG,CREATE_KNOELEADGE_TABLE);
        db.execSQL(CREATE_KNOELEADGE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String CREATE_KNOWLEADGE_TABLE = "DROP TABLE IF EXISTS" + Knowledge.TABLE;

        db.execSQL(CREATE_KNOWLEADGE_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);
    }
    public List<String> getKnowledge() {
        List<String> knowledge = new ArrayList<String>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query
                (Knowledge.TABLE, null, null, null, null, null, null);
        knowledge.add(cursor.getString(3));
//    if (cursor != null) {
//        cursor.moveToFirst();
//    }
//
//    while(!cursor.isAfterLast()) {
//
//        knowledge.add(cursor.getLong(0) + " " +
//                cursor.getLong(1) + " " +
//                cursor.getLong(2) + " " +
//                cursor.getString(3) +" " +
//                cursor.getString(4));
//
        cursor.moveToNext();
//        final String sex = cursor.getString(3);
//    }

        sqLiteDatabase.close();

        return knowledge;
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
