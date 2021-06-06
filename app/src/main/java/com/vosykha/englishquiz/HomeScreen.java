package com.vosykha.englishquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import info.hoang8f.widget.FButton;

public class HomeScreen extends AppCompatActivity{
    FButton playGame,quit,btnMore;
    TextView tQ;


    //firebase và Facebook login
    //private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private CallbackManager fbCallbackManager;
    private LoginButton btnLoginFacebook;
    private static String TAG_FACEBOOK_LOGIN = "FACEBOOK LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        initView();
        setupAction();
        setupLoginFacebook();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        fbCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setupLoginFacebook() {
        fbCallbackManager = CallbackManager.Factory.create();
        btnLoginFacebook.setReadPermissions(Arrays.asList("email", "public_profile"));
        btnLoginFacebook.registerCallback(fbCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Facebook Login", "Login success. Result= "+loginResult);
            }

            @Override
            public void onCancel() {
                Log.d(TAG_FACEBOOK_LOGIN, "Login cancelled");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG_FACEBOOK_LOGIN,"Login fail. Result = "+ error);
            }
        });


        fbCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(fbCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("Facebook Login", "Login success. Result= "+loginResult);
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG_FACEBOOK_LOGIN, "Login cancelled");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d(TAG_FACEBOOK_LOGIN,"Login fail. Result = "+ exception);
                    }
                });

    }
    private void initView() {
        //khởi tạo
        playGame =(FButton)findViewById(R.id.playGame);
        quit = (FButton) findViewById(R.id.quit);
        btnMore = (FButton) findViewById(R.id.btnMore);
        tQ = (TextView)findViewById(R.id.tQ);
        btnLoginFacebook = (LoginButton) findViewById(R.id.btnLoginFacebook);


        //Typeface - đây là phong chữ
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        playGame.setTypeface(typeface);
        quit.setTypeface(typeface);
        btnMore.setTypeface(typeface);
        tQ.setTypeface(typeface);
    }




    public void setupAction() {
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
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreen.this);
                builder.setTitle("Exit");
                builder.setMessage("Are you sure you want to exit?");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HomeScreen.this.finish();
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

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this,ListDatabase.class);
                startActivity(intent);
                finish();
            }
        });
    }
}