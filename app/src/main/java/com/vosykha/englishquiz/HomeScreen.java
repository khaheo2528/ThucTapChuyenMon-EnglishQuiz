package com.vosykha.englishquiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import info.hoang8f.widget.FButton;

public class HomeScreen extends AppCompatActivity {
    FButton playGame,quit;
    TextView tQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        //phương thức sẽ khởi tạo views
        initView();

        //PlayGame button - nút chơi lại
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,MainQuizActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //Quit button- nút thoát
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        //khởi tạo
        playGame =(FButton)findViewById(R.id.playGame);
        quit = (FButton) findViewById(R.id.quit);
        tQ = (TextView)findViewById(R.id.tQ);

        //Typeface - đây là phong chữ
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        playGame.setTypeface(typeface);
        quit.setTypeface(typeface);
        tQ.setTypeface(typeface);
    }
}