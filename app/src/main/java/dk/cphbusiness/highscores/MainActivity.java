package dk.cphbusiness.highscores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MainActivity";
    private HighScoreDb db;

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new HighScoreDb(this);
        db.open();
        Log.d(LOG_TAG, "Main Activity created");
        }

    public void onScoreButtonClicked(View view) {
        EditText nameEdit = (EditText)findViewById(R.id.name_edit);
        EditText scoreEdit = (EditText)findViewById(R.id.score_edit);
        String name = nameEdit.getText().toString();
        int score = Integer.parseInt(scoreEdit.getText().toString());
        long id = db.createHighScore(name, score);
        toast("Highscore created with id #" + id);
        }

    public void onStartButtonClicked(View view) {
        toast("Start button clicked");
        startService(new Intent(this, DowngradeService.class));
        Log.d(LOG_TAG, "Downgrade Service started");
        }

    public void onStopButtonClicked(View view) {
        toast("Stop button clicked");
        stopService(new Intent(this, DowngradeService.class));
        }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
        }

    }
