package com.vosykha.englishquiz.add_edit;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vosykha.englishquiz.ListDatabase;
import com.vosykha.englishquiz.R;

public class EditActivityProduct extends AppCompatActivity {

    EditText edtquestion, edtOptA, edtOptB, edtOptC, edtOptD, edtAnswer;
    Button btnEdit,btnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_edit);
        addViews();
        addEvents();

    }

    private void addEvents() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("question",edtquestion.getText().toString());
                values.put("OptA",edtOptA.getText().toString());
                values.put("OptB",edtOptB.getText().toString());
                values.put("OptC",edtOptC.getText().toString());
                values.put("OptD",edtOptD.getText().toString());
                values.put("Answer",edtAnswer.getText().toString());

                int flag = ListDatabase.database.update("TQ", values, "question =?", new String[]{ListDatabase.selectedTQuestions.getQuestion() +""});
                if(flag>0){
                    Toast.makeText(com.vosykha.englishquiz.add_edit.EditActivityProduct.this, "Update product successful",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(com.vosykha.englishquiz.add_edit.EditActivityProduct.this, "Update product fail",
                            Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });
    }

    private void addViews() {
        edtquestion = findViewById(R.id.edtquestion);
        edtOptA = findViewById(R.id.edtOptA);
        edtOptB = findViewById(R.id.edtOptB);
        edtOptC = findViewById(R.id.edtOptC);
        edtOptD = findViewById(R.id.edtOptD);
        edtAnswer = findViewById(R.id.edtanswer);
        btnEdit = findViewById(R.id.btnEdit);
        btnCancel = findViewById(R.id.btnCancelEdit);

        edtquestion.setText(ListDatabase.selectedTQuestions.getQuestion());
        edtOptA.setText(ListDatabase.selectedTQuestions.getOptA());
        edtOptB.setText(ListDatabase.selectedTQuestions.getOptB());
        edtOptC.setText(ListDatabase.selectedTQuestions.getOptC());
        edtOptD.setText(ListDatabase.selectedTQuestions.getOptD());
        edtAnswer.setText(ListDatabase.selectedTQuestions.getAnswer());

    }
}
