package ai.thanasakis.uda.newsapp.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(this);
        // Insert dummy data, 0 for ID in every event because the db will auto increment the key
        Log.d("Insert of dummy ", "Dummy data inserting in db ..");
        dbHelper.addHabitEvent(new HabitEvent(0, "Drink water", "20-03-2017", "12:11:32", 1, "wasn't that thirsty"));
        dbHelper.addHabitEvent(new HabitEvent(0, "Take vitamins", "22-03-2017", "15:13:23", 2, "forgot about it yesterday"));
        dbHelper.addHabitEvent(new HabitEvent(0, "Called mom", "22-03-2017", "18:00:24", 1, "i should do it more"));
        dbHelper.addHabitEvent(new HabitEvent(0, "Drink water", "23-03-2017", "10:12:32", 1, "wasn't that thirsty"));
        dbHelper.addHabitEvent(new HabitEvent(0, "Run around the block", "23-03-2017", "11:10:58", 5, "5 times in new record!"));

        Log.d("Reading db ", "Reading all db entries");
        List<HabitEvent> habitEvents = dbHelper.getAllHabitEvents();
        int i = 0;
        Log.d("Total Number of Events:", String.valueOf(dbHelper.getHabitEvemtsCount()));
        for (HabitEvent habitEvent : habitEvents) {
            String log = habitEvent.toString();
            Log.d("Event: " + String.valueOf(i++), log);
        }
    }
}

