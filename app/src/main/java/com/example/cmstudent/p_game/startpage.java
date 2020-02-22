package com.example.cmstudent.p_game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class startpage extends AppCompatActivity {
    private Button button;
    private Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toppage);
       //TextView highScoreLabel2 = (TextView) findViewById(R.id.highScoreLabel2);



        // for showing high score
        SharedPreferences settings = getSharedPreferences("GAME_DATA", MODE_PRIVATE);
        int highscore = settings.getInt("HIGH_SCORE", 0);
        TextView highScoreLabel2 = (TextView) findViewById(R.id.highScoreLabel2);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengame();
            }
        });
        highScoreLabel2.setText("High Score : " + highscore);


        //for checker
        button2 = (Button) findViewById(R.id.checkbtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkpage();
            }
        });

    }

    public void opengame() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }

    public void checkpage() {
            Intent intent2 = new Intent(this, checker.class);
            startActivity(intent2);
        }

    }

