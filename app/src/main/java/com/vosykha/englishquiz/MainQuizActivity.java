package com.vosykha.englishquiz;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Collections;
import java.util.List;

import info.hoang8f.widget.FButton;

public class MainQuizActivity extends AppCompatActivity {

    FButton buttonA, buttonB, buttonC, buttonD;
    TextView questionText,TQuizText, timeText, resultText, coinText;
    QuizHelper QuizHelper;
    TQuestions currentQuestion;
    List<TQuestions> list;
    int qid = 0;
    int timeValue = 20;
    int coinValue = 0;
    CountDownTimer countDownTimer;
    Typeface tb, sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_main);

        //Khởi tạo
        questionText = (TextView) findViewById(R.id.TQuestion);
        buttonA = (FButton) findViewById(R.id.buttonA);
        buttonB = (FButton) findViewById(R.id.buttonB);
        buttonC = (FButton) findViewById(R.id.buttonC);
        buttonD = (FButton) findViewById(R.id.buttonD);
        TQuizText = (TextView) findViewById(R.id.TQuizText);
        timeText = (TextView) findViewById(R.id.timeText);
        resultText = (TextView) findViewById(R.id.resultText);
        coinText = (TextView) findViewById(R.id.coinText);

        //thiết lập typefaces cho textview và button
        tb = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");
        sb = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        TQuizText.setTypeface(sb);
        questionText.setTypeface(tb);
        buttonA.setTypeface(tb);
        buttonB.setTypeface(tb);
        buttonC.setTypeface(tb);
        buttonD.setTypeface(tb);
        timeText.setTypeface(tb);
        resultText.setTypeface(sb);
        coinText.setTypeface(tb);

        //hàm Database helper
        QuizHelper = new QuizHelper(this);
        //tạo db writable
        QuizHelper.getWritableDatabase();

        //hàm kiểm tra thêm dữ liệu vào getAllofTheQuestions()
        if (QuizHelper.getAllOfTheQuestions().size() == 0){
            //nếu không có thêm trước đó, thì thêm các câu hỏi và lựa chọn vào bảng
            QuizHelper.allQuestion();
        }

        //Đây sẽ trả lại dữ liệu của TQuestion
        list = QuizHelper.getAllOfTheQuestions();

        //Bây giờ chúng ta sẽ xáo trộn các phần tử của danh sách để chúng ta sẽ nhận được các câu hỏi một cách ngẫu nhiên
        Collections.shuffle(list);

        //currentQuestion sẽ giữ gợi ý và 4 lựa chon và câu trả lời cho từng id riêng biệt
        currentQuestion = list.get(qid);

        //tạo countDownTimer
        countDownTimer = new CountDownTimer(22000,1000) {
            @Override
            public void onTick(long l) {
                timeText.setText(String.valueOf(timeValue) +"\"");

                timeValue -= 1;

                if(timeValue == -1){
                    //Do người dùng đã hết thời gian nên hết giờ hiển thị time_up
                    resultText.setText("Time Up");

                    //họ không thể chọn bất kì nút nào
                    disableButton();
                }
            }

            @Override
            public void onFinish() {
                timeUp();
            }
        }.start();
        //phương thức sẽ thiết lập gợi ý và 4 lựa chọn
        updateQueAndOptions();

    }

    private void updateQueAndOptions() {
        //phương thức sẽ thiết lập setText cho gợi ý và các lựa chọn
        questionText.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOptA());
        buttonB.setText(currentQuestion.getOptB());
        buttonC.setText(currentQuestion.getOptC());
        buttonD.setText(currentQuestion.getOptD());


        timeValue = 20;

        //đặt time up cho cancel và start
        countDownTimer.cancel();
        countDownTimer.start();

        //thiết lập giá trị cho coin text
        coinText.setText(String.valueOf(coinValue));
        coinValue++;

    }

    //thiết lập sự kiện cho button A
    public void buttonA(View view){
        //so sánh lựa chọn với kết quả nếu đúng nút sẽ đổi màu xanh
        if(currentQuestion.getOptA().equals(currentQuestion.getAnswer())){
            buttonA.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            //kiểm trả nếu người dùng không vượt quá giới hạn hàng đợi
            if (qid <list.size()-1){
                disableButton();
                correctDialog();
            }
            else{
                gameWon();
            }
        }
        else {
            gameLostPlayAgain();
        }
    }
    //thiết lập sự kiện cho button B
    public void buttonB(View view){
        //so sánh lựa chọn với kết quả nếu đúng nút sẽ đổi màu xanh
        if(currentQuestion.getOptB().equals(currentQuestion.getAnswer())){
            buttonB.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            //kiểm trả nếu người dùng không vượt quá giới hạn hàng đợi
            if (qid <list.size()-1){
                disableButton();
                correctDialog();
            }
            else{
                gameWon();
            }
        }
        else {
            gameLostPlayAgain();
        }
    }

    public void buttonC(View view) {
        if (currentQuestion.getOptC().equals(currentQuestion.getAnswer())) {
            buttonC.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {

            gameLostPlayAgain();
        }
    }

    public void buttonD(View view){
            if (currentQuestion.getOptD().equals(currentQuestion.getAnswer())) {
                buttonD.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
                if (qid < list.size() - 1) {
                    disableButton();
                    correctDialog();
                } else {
                    gameWon();
                }
            } else {
                gameLostPlayAgain();
            }
    }



    private void gameLostPlayAgain() {
        Intent intent = new Intent(this, PlayAgain.class);
        startActivity(intent);
        finish();
    }

    private void gameWon() {
        Intent intent = new Intent(this, GameWon.class);
        startActivity(intent);
        finish();
    }

    private void timeUp() {
        Intent intent = new Intent(this, Time_Up.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
        finish();
    }


    private void correctDialog() {
        final Dialog dialogCorrect = new Dialog(MainQuizActivity.this);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null){
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.dialog_correct);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();

        onPause();

        TextView correctText = (TextView) dialogCorrect.findViewById(R.id.correctText);
        FButton buttonNext = (FButton) dialogCorrect.findViewById(R.id.dialogNext);

        //thiết lập typeface
        correctText.setTypeface(sb);
        buttonNext.setTypeface(sb);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCorrect.dismiss();
                qid++;
                currentQuestion = list.get(qid);
                updateQueAndOptions();
                resetColor();
                enableButton();
            }
        });
    }

    //phương thức sẽ làm màu của nút trắng trở lại khi nó đã chuyển sang màu xanh
    public void resetColor() {
        buttonA.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonB.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonC.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonD.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
    }


    //phương thức này sẽ vô hiệu hóa nút lựa chọn
    private void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }

    //phương thức này sẽ kích hoạt nút lựa chọn
    public void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }


}