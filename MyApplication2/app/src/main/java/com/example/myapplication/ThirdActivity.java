package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    String dataSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        long TIME_USED = getIntent().getLongExtra("TIME_USED", 600000);


        TextView timeUsed_text = findViewById(R.id.elapsedTime);
        timeUsed_text.setVisibility(View.VISIBLE);

            int minutes = (int) TIME_USED / 60000;
            int seconds = (int) TIME_USED % 60000 / 1000;

            String timeUsedText;
            timeUsedText = " Заврши за " + minutes + " минути и ";
            timeUsedText += seconds + " секунди ";

            timeUsed_text.setText(timeUsedText);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                dataSaved= null;
            } else {
                dataSaved= extras.getString("Key");
            }
        } else {
            dataSaved= (String) savedInstanceState.getSerializable("Key");
        }

        //imageView with Next button
        ImageView imageView = (ImageView)findViewById(R.id.next_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataSaved != null){
                    switch (dataSaved) {
                        case "DataFifth": {
                            Intent act2 = new Intent(view.getContext(), FifthActivity.class);
                            startActivity(act2);
                            break;
                        }
                        case "DataSixth": {
                            Intent act2 = new Intent(view.getContext(), SixthActivity.class);
                            startActivity(act2);
                            break;
                        }
                        case "DataSeventh": {
                            Intent act2 = new Intent(view.getContext(), SeventhActivity.class);
                            startActivity(act2);
                            break;
                        }
                        case "DataEighth": {
                            Intent act2 = new Intent(view.getContext(), EighthActivity.class);
                            startActivity(act2);
                            break;
                        }
                    }

                }else {
                    Intent act2 = new Intent(view.getContext(), FourthActivity.class);
                    startActivity(act2);
                }

            }

        });

    }

}