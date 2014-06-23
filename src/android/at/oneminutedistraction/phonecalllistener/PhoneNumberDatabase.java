package at.oneminutedistraction.phonecalllistener;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import static at.oneminutedistraction.phonecalllistener.Constants.*;

/**
 * Created by cmlee on 6/23/14.
 */
public class PhoneNumberDatabase {

    private final PhoneNumberTrackerOpenHelper tracker;

    public PhoneNumberDatabase(Context context) {
        tracker = new PhoneNumberTrackerOpenHelper(context);
    }

    public boolean isRegistered(String pnumber) {
        return (!select(pnumber, SQL_COLUMN_PHONENUMBER).isEmpty());
    }

    public List<PhoneNumber> getById(String id) {
        return (select(id, SQL_COLUMN_ID));
    }

    public List<PhoneNumber> getByPhoneNumber(String pnumber) {
        return (select(pnumber, SQL_COLUMN_PHONENUMBER));
    }

    public List<PhoneNumber> getAll() {
        final List<PhoneNumber> result = new LinkedList<PhoneNumber>();
        final SQLiteDatabase database = tracker.getReadableDatabase();
        final Cursor cursor = database.rawQuery("select * from " + SQL_TABLE_PHONENUMBER, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            PhoneNumber pn = new PhoneNumber();
            pn.setId(cursor.getString(0));
            pn.setPhoneNumber(cursor.getString(1));
            pn.setNotes(cursor.getString(2));
            result.add(pn);
        }
        return (result);
    }

    private List<PhoneNumber> select(String value, String colName) {
        final List<PhoneNumber> result = new LinkedList<PhoneNumber>();
        final SQLiteDatabase database = tracker.getReadableDatabase();
        final Cursor cursor = database.query(SQL_TABLE_PHONENUMBER,
                new String[] { SQL_COLUMN_ID, SQL_COLUMN_PHONENUMBER, SQL_COLUMN_NOTES },
                colName + " like ?",
                new String[]{ "%" + value + "%"}, null, null, null, null);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            PhoneNumber pn = new PhoneNumber();
            pn.setId(cursor.getString(0));
            pn.setPhoneNumber(cursor.getString(1));
            pn.setNotes(cursor.getString(2));
            result.add(pn);
        }
        cursor.close();
        database.close();
        return (result);
    }

    public boolean addPhoneNumber(String id, String pnumber, String notes) {
        return (addPhoneNumber(id, pnumber, notes, false));
    }

    public boolean addPhoneNumber(String id, String pnumber, String notes, boolean overwrite) {
        List<PhoneNumber> result = getById(id);
        if (!result.isEmpty()) {
            if (!overwrite)
                return (false);
            PhoneNumber n = result.get(0);
            removePhoneNumber(n.getId());
        }

        final SQLiteDatabase database = tracker.getWritableDatabase();
        final ContentValues values = new ContentValues();
        values.put(SQL_COLUMN_ID, id);
        values.put(SQL_COLUMN_PHONENUMBER, pnumber);
        values.put(SQL_COLUMN_NOTES, notes);

        database.insert(SQL_TABLE_PHONENUMBER, null, values);
        database.close();

        return (true);
    }

    public PhoneNumber removePhoneNumber(String id) {
        List<PhoneNumber> result = getById(id);
        if (!result.isEmpty())
            return (null);
        final SQLiteDatabase database = tracker.getWritableDatabase();
        database.delete(SQL_TABLE_PHONENUMBER, SQL_COLUMN_ID + " = ?",
                new String[] { id });
        database.close();
        return (result.get(0));
    }

}
