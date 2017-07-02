package ai.thanasakis.uda.newsapp.habittracker;

import android.provider.BaseColumns;

/**
 * Created by programbench on 7/3/2017.
 */

public class TrackerContract {
    //empty constructor
    public TrackerContract() {
    }

    public static final class TrackerItem implements BaseColumns {

        /**
         * Table Name of DB
         */
        public final static String TABLE_NAME = "mainTable";

        /**
         * Entry ID for private use.
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the habit.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_NAME = "name";

        /**
         * Date the event took place.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_DATE = "date";

        /**
         * Time the event took place.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_TIME = "time";

        /**
         * Number of repetitions of the habit in this event.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_REPETITIONS = "repetitions";

        /**
         * User Comments about the specific habit.
         * <p>
         * Type:  TEXT
         */
        public final static String COLUMN_COMMENTS = "comments";


    }

}
