package dk.cphbusiness.highscores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import static dk.cphbusiness.highscores.HighScoreDbHelper.*;

public class HighScoreDb {
	private static final String LOG_TAG = "HighScoreDb";
	private SQLiteDatabase db;
	private final Context context;
	private final HighScoreDbHelper helper;
	
	public HighScoreDb(Context context) {
		this.context = context;
		helper = new HighScoreDbHelper(context);
		}
	
	public void close() { db.close(); }
	
	public void open() {
		try {
			db = helper.getWritableDatabase();
			}
		catch (SQLiteException sqle) {
			Log.w(LOG_TAG, "Could not open database for writing "+sqle.getMessage());
			// Log.println(Log.WARN, LOG_TAG, "message... ");
			db = helper.getReadableDatabase();
			}
		}
	
	public long createHighScore(String name, int score) {
		try {
			ContentValues values = new ContentValues();
			values.put(NAME_COLUMN, name);
			values.put(SCORE_COLUMN, score);
			return db.insert(TABLE, null, values);
			}
		catch (SQLiteException sqle) {
			Log.w(LOG_TAG, "Could not create high score "+sqle.getMessage());
			return -1;
			}
		}
	
	public Cursor getHighScores() {
		return db.rawQuery("select * from "+TABLE, null);
		}
	
	}
