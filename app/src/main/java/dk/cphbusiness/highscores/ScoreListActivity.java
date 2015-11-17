package dk.cphbusiness.highscores;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import static dk.cphbusiness.highscores.HighScoreDbHelper.*;

public class ScoreListActivity extends AppCompatActivity {
    private HighScoreDb db;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private ListView scoreList;
    private static final String[] FROM = { ID_COLUMN, NAME_COLUMN, SCORE_COLUMN };
    private static final int[] TO = { R.id.id_item_label, R.id.name_item_label, R.id.score_item_label};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);
        scoreList = (ListView)findViewById(R.id.score_list);
        db = new HighScoreDb(this);
        db.open();
        }

    @Override
    protected void onResume() {
        super.onResume();

        cursor = db.getHighScores();
        startManagingCursor(cursor);
        adapter = new SimpleCursorAdapter(this, R.layout.higscore_item, cursor, FROM, TO, 0);
        scoreList.setAdapter(adapter);
        }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
        }

    }
