package dk.cphbusiness.highscores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private HighScoreDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new HighScoreDb(this);
        db.open();
        }

    public void onScoreButtonClicked(View view) {
        EditText nameEdit = (EditText)findViewById(R.id.name_edit);
        EditText scoreEdit = (EditText)findViewById(R.id.score_edit);
        String name = nameEdit.getText().toString();
        int score = Integer.parseInt(scoreEdit.getText().toString());
        long id = db.createHighScore(name, score);
        Toast.makeText(this, "Highscore created with id #"+id, Toast.LENGTH_LONG).show();
        }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
        }

    }
