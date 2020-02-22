package com.example.cmstudent.p_game;

import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;


    //activity view
    //TextView timer;
    ImageView imgview;
    TextView mTextTv, spoken;
    ImageButton mmicButton;
    Chronometer chronometer;
    boolean running;
    ArrayList<String> ar;
    int questionNumber = 0;
    //image
     int current_image;
    int[] images ={R.drawable.bomb3,R.drawable.angry_bomb};


    private int point = 0;

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.Btime);
        //imageviewchange
        imgview=(ImageView)findViewById(R.id.imageView4);

        //speech to text

        mTextTv = findViewById(R.id.answer);
        spoken = findViewById(R.id.spoken);

        mmicButton = findViewById(R.id.micButton);


        // button click to show speech to text dialog
        mmicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "time start", Toast.LENGTH_SHORT).show();
                // countDownTimer.start();
                chronometer.start();
                speak();
                setMmicButton();
                //imageview
//                current_image++;
//                current_image=current_image % images.length;
                imgview.setImageResource(images[1]);
            }
        });
        // Words to say
        ar = new ArrayList<String>();
        String s1 = "coffee";
        String s2 = "bag";
        String s3 = "computer";
        ar.add(s1);
        ar.add(s2);
        ar.add(s3);

        String s4 = "mobile";
        ar.add(s4);
        String s5 = "laptop";
        String s6 = "hello";
        String s7 = "lion";
        String s8 = "perfect";
        String s9 = "bottle";
        String s10 = "paper";
        String s11 = "human";
        String s12 = "milk";
        String s13 = "video";
        String s14 = "blue";
        String s15 = "soccer";
        String s16 = "eligibility";
        ar.add(s5);
        ar.add(s6);
        ar.add(s7);
        ar.add(s8);
        ar.add(s9);
        ar.add(s10);
        ar.add(s11);
        ar.add(s12);
        ar.add(s13);
        ar.add(s14);
        ar.add(s15);
        ar.add(s16);

        Collections.shuffle(ar);

        spoken.setText(ar.get(questionNumber));


        intent = new Intent(MainActivity.this, lastPage.class);

    }

    //button for timer


    public void setMmicButton() {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            running = true;


            chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                // for 50 seconds
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    if (chronometer.getText().toString().equalsIgnoreCase("00:10")) {
//                                setContentView(R.layout.lastpage);

                        //imagechange

                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }


    private void speak() {

        //intent to show speech to text dialog

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "try to pronounce");

        try {
            // in there was no error
            //show dialog
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            //if error
            //get message
            // new
            //intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(("https://play.google.com/store/apps/details?id=" + appPackageName)))

          //  Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    //receive voice input and handle it


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //set to text view
                    mTextTv.setText(result.get(0));


//                    for (int i=0; i<ar.size();i++) {
//                        spoken.setText(ar.get(i));
//                    }


                    if (result.get(0).equals(spoken.getText().toString())) {
                        // Toast.makeText(this,"good", Toast.LENGTH_LONG).show();
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
                        a_builder.setMessage("Correct !").show();
                        point++;
                        intent.putExtra("pointKey",point);

                        TextView pointView = findViewById(R.id.pointView);
                        pointView.setText("Points:" + String.valueOf(point));
                        questionNumber++;
                        spoken.setText(ar.get(questionNumber));


                    } else {
                        Toast.makeText(this, "wrong", Toast.LENGTH_LONG).show();
                    }


                }
                break;
            }
        }
    }
}
