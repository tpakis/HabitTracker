package ai.thanasakis.uda.newsapp.habittracker;

/**
 * Created by programbench on 7/3/2017.
 */

public class HabitEvent {
    //private variables
    private int _id;
    private String _name;
    private String _date;
    private String _time;
    private int _repetitions;
    private String _comments;

    public HabitEvent(int _id, String _name, String _date, String _time, int _repetitions, String _comments) {
        this._id = _id;
        this._name = _name;
        this._date = _date;
        this._time = _time;
        this._repetitions = _repetitions;
        this._comments = _comments;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }

    public int get_repetitions() {
        return _repetitions;
    }

    public void set_repetitions(int _repetitions) {
        this._repetitions = _repetitions;
    }

    public String get_comments() {
        return _comments;
    }

    public void set_comments(String _comments) {
        this._comments = _comments;
    }

    @Override
    public String toString() {
        return "HabitEvent{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _date='" + _date + '\'' +
                ", _time='" + _time + '\'' +
                ", _repetitions=" + _repetitions +
                ", _comments='" + _comments + '\'' +
                '}';
    }
}
