package br.com.udacity.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.udacity.habittracker.data.HabitContract;
import br.com.udacity.habittracker.data.HabitDAO;

public class MainActivity extends AppCompatActivity {

    private HabitDAO dao;
    private HabitTracker habit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<HabitTracker> habitTracker = new ArrayList<HabitTracker>();
        habitTracker.add(new HabitTracker("Caminhar", 5));
        habitTracker.add(new HabitTracker("Correr", 3));
        habitTracker.add(new HabitTracker("Ir ao curso", 2));
        habitTracker.add(new HabitTracker("Tomar remédio", 3));
        habitTracker.add(new HabitTracker("Natação", 5));

        for (Iterator iterator = habitTracker.iterator(); iterator.hasNext();) {
            habit = (HabitTracker) iterator.next();
            insert();
        }
        displayDatabase();
    }

    public void insert() {

        dao = new HabitDAO(this);
        SQLiteDatabase db = dao.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntries.COLUMN_DESCRIPTION, habit.getDescriptionHabit());
        values.put(HabitContract.HabitEntries.COLUMN_QUANTITY_WEEK, habit.getQuantityWeek());

        db.insert(HabitContract.HabitEntries.TABLE_NAME, null, values);
        db.close();
    }

    public void displayDatabase() {
        dao = new HabitDAO(this);
        SQLiteDatabase db = dao.getReadableDatabase();

        String[] projection = {
                HabitContract.HabitEntries._ID,
                HabitContract.HabitEntries.COLUMN_DESCRIPTION,
                HabitContract.HabitEntries.COLUMN_QUANTITY_WEEK
        };

        Cursor cursor = db.query(HabitContract.HabitEntries.TABLE_NAME,
                projection, null, null, null, null, null);

        TextView displayHabit = (TextView) findViewById(R.id.txt_habit);

        try {

            displayHabit.append("\n" + HabitContract.HabitEntries._ID + " " +
                    HabitContract.HabitEntries.COLUMN_DESCRIPTION + " " +
                    HabitContract.HabitEntries.COLUMN_QUANTITY_WEEK + "\n\n");

            int idColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntries._ID);
            int descriptionColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntries.COLUMN_DESCRIPTION);
            int quantityColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntries.COLUMN_QUANTITY_WEEK);

            while (cursor.moveToNext()) {

                int id = cursor.getInt(idColumnIndex);
                String descriptionHabit = cursor.getString(descriptionColumnIndex);
                int quantityHabit = cursor.getInt(quantityColumnIndex);

                displayHabit.append(id + " " + descriptionHabit + " " + quantityHabit + "\n\n");
            }

        } finally {
            cursor.close();
            db.close();
        }

    }
}
