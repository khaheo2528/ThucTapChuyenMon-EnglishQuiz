package com.vosykha.englishquiz;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.vosykha.englishquiz.adapter.Databases;

import java.util.ArrayList;

public class ListDatabase extends AppCompatActivity {

    Databases databases;
    ListView LvDatabase;
    QuizHelper QuizHelper;
    ArrayList<TQuestions> taskArrayList;

    //TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_database);

        addViews();
        getData();

    }

    private void getData() {
        Cursor c = databases.GetData("SELECT * FROM TQ");
        taskArrayList.clear();
        while (c.moveToNext()){
            //id = 0;
            //        question = "";
            //        opta = "";
            //        optb = "";
            //        optc = "";
            //        optd = "";
            //        answer = "";
            int id = c.getInt(0);
            String question = c.getString(1);
            String opta = c.getString(2);
            String optb = c.getString(3);
            String optc = c.getString(4);
            String optd = c.getString(5);
            String answer = c.getString(6);

        }
    }


    private void addViews() {
        LvDatabase = (ListView) findViewById(R.id.lvDatabase);


    }
}