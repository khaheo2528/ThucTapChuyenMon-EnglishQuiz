package com.vosykha.englishquiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayAgain extends AppCompatActivity {
    Button playAgain;
    TextView wrongAnsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_again);

        //khởi tạo
        playAgain = (Button) findViewById(R.id.playAgainButton);
        wrongAnsText = (TextView)findViewById(R.id.wrongAns);

        //play again button onclick listener
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayAgain.this, MainQuizActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Setting typefaces for textview and button - this will give stylish fonts on textview and button
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        playAgain.setTypeface(typeface);
        wrongAnsText.setTypeface(typeface);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}