package com.vosykha.englishquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GameWon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_won);
    }

    public void PlayAgain(View view){
        Intent intent = new Intent(GameWon.this, MainQuizActivity.class);
        startActivity(intent);
        finish();
    }
}