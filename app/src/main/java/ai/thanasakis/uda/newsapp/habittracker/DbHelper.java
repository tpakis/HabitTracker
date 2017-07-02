package ai.thanasakis.uda.newsapp.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ai.thanasakis.uda.newsapp.habittracker.TrackerContract.TrackerItem;

/**
 * Created by programbench on 7/3/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "HabitTracker.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating the most frequently used methods although not used in this specific app

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HABITS_TABLE = "CREATE TABLE " + TrackerItem.TABLE_NAME + " ("
                + TrackerItem._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TrackerItem.COLUMN_NAME + " TEXT,"
                + TrackerItem.COLUMN_DATE + " TEXT,"
                + TrackerItem.COLUMN_TIME + " TEXT,"
                + TrackerItem.COLUMN_REPETITIONS + " INTEGER,"
                + TrackerItem.COLUMN_COMMENTS + " TEXT" + ")";
        db.execSQL(CREATE_HABITS_TABLE);
        Log.d("Db Creation", "Success");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TrackerItem.TABLE_NAME);

        // Create tables again
        onCreate(db);
        Log.d("Db Update", "Success");
    }

    // Adding new Habit Event in DB
    void addHabitEvent(HabitEvent habitEvent) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TrackerItem.COLUMN_NAME, habitEvent.get_name()); // Event Name
        values.put(TrackerItem.COLUMN_DATE, habitEvent.get_date()); // Event Date
        values.put(TrackerItem.COLUMN_TIME, habitEvent.get_time()); // Event Time
        values.put(TrackerItem.COLUMN_REPETITIONS, habitEvent.get_repetitions()); // Event Repetitions
        values.put(TrackerItem.COLUMN_COMMENTS, habitEvent.get_comments()); // Event Name

        // Inserting Row
        db.insert(TrackerItem.TABLE_NAME, null, values);
        db.close();
        Log.d("Db Add Habit", "Success");
    }

    // Delete Habit Event
    public void deleteContact(HabitEvent habitEvent) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TrackerItem.TABLE_NAME, TrackerItem._ID + " = ?", new String[]{String.valueOf(habitEvent.get_id())});
        db.close();
        Log.d("Db Delete Habit", "Success");
    }

    // Get All Entries
    public List<HabitEvent> getAllHabitEvents() {
        List<HabitEvent> habitsList = new ArrayList<HabitEvent>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + TrackerItem.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HabitEvent habitEvent = new HabitEvent(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        Integer.parseInt(cursor.getString(4)),
                        cursor.getString(5));
                habitsList.add(habitEvent);
            } while (cursor.moveToNext());
        }
        return habitsList;
    }

    // Get specific habit by ID
    public HabitEvent getHabitEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TrackerItem.TABLE_NAME, new String[]{TrackerItem._ID,
                TrackerItem.COLUMN_NAME,
                TrackerItem.COLUMN_DATE,
                TrackerItem.COLUMN_TIME,
                TrackerItem.COLUMN_REPETITIONS,
                TrackerItem.COLUMN_COMMENTS}, TrackerItem._ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        HabitEvent habitEvent = new HabitEvent(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                Integer.parseInt(cursor.getString(4)),
                cursor.getString(5));
        return habitEvent;
    }

    // Update specific habit
    public int updateEvent(HabitEvent habitEvent) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TrackerItem.COLUMN_NAME, habitEvent.get_name()); // Event Name
        values.put(TrackerItem.COLUMN_DATE, habitEvent.get_date()); // Event Date
        values.put(TrackerItem.COLUMN_TIME, habitEvent.get_time()); // Event Time
        values.put(TrackerItem.COLUMN_REPETITIONS, habitEvent.get_repetitions()); // Event Repetitions
        values.put(TrackerItem.COLUMN_COMMENTS, habitEvent.get_comments()); // Event Name

        return db.update(TrackerItem.TABLE_NAME, values, TrackerItem._ID + " = ?", new String[]{String.valueOf(habitEvent.get_id())});
    }

    // Get Events Count
    public int getHabitEvemtsCount() {
        int count;
        String cQuery = "SELECT  * FROM " + TrackerItem.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(cQuery, null);
        count = cursor.getCount();
        cursor.close();

        return count;
    }
}
