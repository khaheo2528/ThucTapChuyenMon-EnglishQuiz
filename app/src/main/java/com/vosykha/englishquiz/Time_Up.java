package com.vosykha.englishquiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import info.hoang8f.widget.FButton;

public class Time_Up extends AppCompatActivity {
    FButton playAgainButton;
    TextView timeUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_up);

        //khỏi tạo
        playAgainButton = (FButton) findViewById(R.id.playAgainButton);
        timeUpText = (TextView) findViewById(R.id.timeUpText);
        //tạo sự kiện
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Time_Up.this,MainQuizActivity.class);
                startActivity(intent);
                finish();

            }
        });

        //Thiết lập typeface cho textview và button ;nó sẽ đưa phong chữ vào textview và button
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        timeUpText.setTypeface(typeface);
        playAgainButton.setTypeface(typeface);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}