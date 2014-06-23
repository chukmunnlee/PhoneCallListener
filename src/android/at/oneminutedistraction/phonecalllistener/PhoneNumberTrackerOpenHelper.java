package at.oneminutedistraction.phonecalllistener;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static at.oneminutedistraction.phonecalllistener.Constants.*;

/**
 * Created by cmlee on 6/23/14.
 */
public class PhoneNumberTrackerOpenHelper extends SQLiteOpenHelper {

    PhoneNumberTrackerOpenHelper(Context context) {
        super(context, DB_PHONENUMBER, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Creating " + DB_PHONENUMBER);
        db.execSQL("create table " + SQL_TABLE_PHONENUMBER +
                " (" + SQL_COLUMN_ID + " text primary key, " +
                SQL_COLUMN_PHONENUMBER + ", " +
                SQL_COLUMN_NOTES + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "Upgrade " + DB_PHONENUMBER + " from " + oldVersion + " to " + newVersion);
    }
}
