package com.vosykha.englishquiz.register;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.vosykha.englishquiz.HomeScreen;
import com.vosykha.englishquiz.R;

import info.hoang8f.widget.FButton;

public class Login extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btnLogin;
    TextView creText2;
    ProgressBar progressBar2;
    FirebaseAuth mAuth;
    FButton quit;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        progressBar2 = findViewById(R.id.progressBar2);
        mAuth =  FirebaseAuth.getInstance();
        btnLogin = findViewById(R.id.btnLogin);
        creText2 = findViewById(R.id.creText2);
        quit = findViewById(R.id.quit);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    edtEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    edtPassword.setError("Password is Required");
                    return;
                }
                if (password.length()<6){
                    edtPassword.setError("Password must be >= 6 characters");
                    return;
                }

                progressBar2.setVisibility(View.VISIBLE);

                //authenticate the user
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged in successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                        }
                        else {
                            Toast.makeText(Login.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });


        creText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setTitle("Exit");
                builder.setMessage("Are you sure you want to exit?");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Login.this.finish();

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }


}