package br.com.udacity.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class HabitDAO extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "habit.db";
    public static final int DATABASE_VERSION = 7;
    private static final String SQL_CREATE =
            "CREATE TABLE " + HabitContract.HabitEntries.TABLE_NAME +
                    " ( " +
                    HabitContract.HabitEntries._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                    HabitContract.HabitEntries.COLUMN_DESCRIPTION + " TEXT , " +
                    HabitContract.HabitEntries.COLUMN_QUANTITY_WEEK + " INTEGER DEFAULT 0"
                    + "); ";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + HabitContract.HabitEntries.TABLE_NAME;

    public HabitDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
