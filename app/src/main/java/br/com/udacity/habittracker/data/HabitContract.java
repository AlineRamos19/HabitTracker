package br.com.udacity.habittracker.data;


import android.provider.BaseColumns;

public final class HabitContract {

    private HabitContract(){}

    public static final class HabitEntries implements BaseColumns{

        public static final String TABLE_NAME = "habitTracker";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_DESCRIPTION = "descriptionHabit";
        public static final String COLUMN_QUANTITY_WEEK = "QuantityWeek";
    }
}
