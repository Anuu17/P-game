package com.example.cmstudent.p_game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class lastPage extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lastpage);

        Intent intent = getIntent();
        int point = intent.getIntExtra("pointKey",0);

        TextView pointView = findViewById(R.id.textView2);
        pointView.setText("Points: " + String.valueOf(point));
// high score
        TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);

        SharedPreferences settings = getSharedPreferences("GAME_DATA", MODE_PRIVATE);
        int highscore = settings.getInt("HIGH_SCORE", 0);

        if (point > highscore) {
            highScoreLabel.setText("High Score :" + point);

            //Save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", point);
            editor.commit();

        } else {
            highScoreLabel.setText("High Score : " + highscore);
        }

        button = (Button) findViewById(R.id.reBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(lastPage.this, "clicked!", Toast.LENGTH_SHORT).show();
                restart();
            }
        });
    }
    public void restart() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

