package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SecondActivity extends AppCompatActivity{

    private  ProgressBar progressBar;
    private  TextView countdown_text;
    private long timeLeftInMilliSeconds = 600000; // 10mins

    Handler handler;
    Handler handler2;
    Runnable runnable;
    Runnable runnable2;
    Timer timer;
    Timer timer2;

    int i=0;

@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);

    progressBar = (ProgressBar) findViewById(R.id.progressBar);
    progressBar.setVisibility(View.VISIBLE);

    progressBar.setProgress(0);
    progressBar.setMax(100);

    handler = new Handler();
    runnable = new Runnable() {
        @Override
        public void run() {
            if(i<=100){
                progressBar.setProgress(i);
                progressBar.setSecondaryProgress(i+10);
            }else{
                progressBar.setVisibility(View.VISIBLE);
                timer.cancel();
            }

        }
    };
    timer = new Timer();
    timer.schedule(new TimerTask() {
        @Override
        public void run() {
            i++;
            handler.post(runnable);
        }
    }, 4000, 400);

   CountDownTimer countDownTimer =  new CountDownTimer(timeLeftInMilliSeconds, 1000) {

        public void onTick(long l) {
            timeLeftInMilliSeconds = l;
            updateTimer();
        }

        public void onFinish() {
            countdown_text.setText("Браво!");
        }
    }.start();


    countdown_text = (TextView) findViewById(R.id.countdown_text);
    countdown_text.setVisibility(View.VISIBLE);


    handler2 = new Handler();
    runnable2 = new Runnable() {
        @Override
        public void run() {
                countdown_text.setVisibility(View.VISIBLE);
                timer2.cancel();
        }
    };
    timer2 = new Timer();
    timer2.schedule(new TimerTask() {
        @Override
        public void run() {

            handler2.post(runnable2);
        }
    }, 4000, 400);

    //imageView with Next button
    ImageView imageView = (ImageView)findViewById(R.id.next_button);
    imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent act2 = new Intent(view.getContext(), ThirdActivity.class);
            act2.putExtra("TIME_USED", 600000 - timeLeftInMilliSeconds);
            startActivity(act2);
        }
    });

    //imageView with Stop button
    ImageView imageViewStop = (ImageView)findViewById(R.id.stop_button);
    imageViewStop.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent act3 = new Intent(view.getContext(), MainActivity.class);
            startActivity(act3);
        }
    });

}

    public void updateTimer() {

        int minutes = (int)timeLeftInMilliSeconds/60000;
        int seconds = (int)timeLeftInMilliSeconds % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + minutes + ":";
        if(seconds < 10) timeLeftText +="0";
        timeLeftText += seconds + " ";

        countdown_text.setText(timeLeftText);
    }

}

