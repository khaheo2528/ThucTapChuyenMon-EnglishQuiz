package com.vosykha.englishquiz.add_edit;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vosykha.englishquiz.ListDatabase;
import com.vosykha.englishquiz.R;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class AddActivityProduct extends AppCompatActivity {

    EditText edtquestion, edtOptA, edtOptB, edtOptC, edtOptD, edtAnswer;
    Button btnOk,btnCancel;
    //new
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String QuizID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_database);

        addViews();
        addEvents();
    }

    private void addViews() {
        edtquestion = findViewById(R.id.edtquestion);
        edtOptA = findViewById(R.id.edtOptA);
        edtOptB = findViewById(R.id.edtOptB);
        edtOptC = findViewById(R.id.edtOptC);
        edtOptD = findViewById(R.id.edtOptD);
        edtAnswer = findViewById(R.id.edtanswer);
        btnOk = findViewById(R.id.btnOK);
        btnCancel = findViewById(R.id.btnCancel);
        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }


    private void addEvents() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


       btnOk.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String pquestion = edtquestion.getText().toString();
               String pOptA = edtOptA.getText().toString();
               String pOptB = edtOptB.getText().toString();
               String pOptC = edtOptC.getText().toString();
               String pOptD = edtOptD.getText().toString();
               String pAnswer = edtAnswer.getText().toString();

               ContentValues values = new ContentValues();
               values.put("question",pquestion);
               values.put("OptA",pOptA);
               values.put("OptB",pOptB);
               values.put("OptC",pOptC);
               values.put("OptD",pOptD);
               values.put("Answer",pAnswer);
               long flag = ListDatabase.database.insert("TQ", null, values);
               if (flag > 0)
                   Toast.makeText(com.vosykha.englishquiz.add_edit.AddActivityProduct.this, "Add product successful!", Toast.LENGTH_LONG).show();
               else {
                   Toast.makeText(com.vosykha.englishquiz.add_edit.AddActivityProduct.this, "Add Product fail", Toast.LENGTH_LONG).show();
                   finish();
               }


               //QuizID = mAuth.getUid();
               DocumentReference documentReference = fStore.collection("TQuiz").document(QuizID);
               Map<String, Object> q = new HashMap<>();
               q.put("question", pquestion);
               q.put("OptA",pOptA);
               q.put("OptB",pOptB);
               q.put("OptC",pOptC);
               q.put("OptD",pOptD);
               q.put("Answer",pAnswer);
               documentReference.set(q).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void aVoid) {
                       Log.d(TAG, "onSuccess: user profile is created for"+ QuizID  );
                   }
               });

           }
       });
    }
}