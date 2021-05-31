package com.vosykha.englishquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class QuizHelper extends SQLiteOpenHelper {

    //khai bao Database name
    private Context context;
    private static final String DB_NAME ="TQuiz.db";

    //chúng ta có thểm các câu hỏi bạn muốn vào table
    //hoặc bất kì sự điều chỉnh nào trong database chỉ cần không ảnh hướng tới version
    private static final int DB_VERSION = 3;
    //Tên bảng
    private static final String TABLE_NAME = "TQ";
    //ID
    private static final String UID = "_UID";
    //Câu hỏi
    private static final String QUESTION = "QUESTION";
    //Câu A
    private static final String OPTA = "OPTA";
    //Câu B
    private static final String OPTB = "OPTB";
    //Câu C
    private static final String OPTC = "OPTC";
    //Câu D
    private static final String OPTD = "OPTD";
    //Câu trả lời
    private static final String ANSWER = "ANSWER";
    // Cho nên về cơ bản ta đang tạo bảng với 1st column-id , 2nd column-câu hỏi , 3rd column -câu A, 4th column -câu B , 5th column -câu C , 6th column -câu D , 7th column - câu trả lời
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    //table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    QuizHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Hàm onCreate chỉ được gọi 1 lần
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Hàm onUpgrade được gọi khi chúng ta cập nhật hoặc tăng database
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    void allQuestion(){
        ArrayList<TQuestions> arrayList = new ArrayList<>();
        //c1
        arrayList.add(new TQuestions("If you have a problem with your computer hardware, call the ______ technician.", "check", "diagnose", "send", "support", "support"));
        //c2
        arrayList.add(new TQuestions("I ________ my emails twice a day to see if anyone has sent me a message.", "check", "diagnose", "send", "support", "check"));
        //c3
        arrayList.add(new TQuestions("First, ________ the problem. Then solve it.", "check", "diagnose", "send", "provide", "diagnose"));
        //c4
        arrayList.add(new TQuestions("In our company we ________ support to users of all kinds of operating systems.", "check", "diagnose", "send", "provide", "provide"));
        //c5
        arrayList.add(new TQuestions("I travel a lot in my role as salesperson because my company has many ________ in different countries.", "agenda", "clients", "occasionally", "characters", "agenda"));
        //c6
        arrayList.add(new TQuestions("Make sure you read the ________ before the meeting so that you know what we’ll talk about.", "agenda", "clients", "occasionally", "characters", "clients"));
        //c7
        arrayList.add(new TQuestions("Passwords must be eight or more ________ long, including both letters and numbers.", "agenda", "clients", "occasionally", "characters", "characters"));
        //c8
        arrayList.add(new TQuestions("I’m usually at my desk but I ________ go out to visit a supplier.", "agenda", "clients", "occasionally", "characters", "occasionally"));

        //c9
        arrayList.add(new TQuestions("A(n) ________ looks after databases.", "database administrator", "project manager", "systems analyst", "helpdesk supervisor", "database administrator"));
        //c10
        arrayList.add(new TQuestions("A(n)________ writes specifications for software.", "database administrator", "project manager", "systems analyst", "helpdesk supervisor", "systems analyst"));
        //c11
        arrayList.add(new TQuestions("A(n)________ manages projects.", "database administrator", "project manager", "systems analyst", "helpdesk supervisor", "project manager"));
        //c12
        arrayList.add(new TQuestions("A(n)________ manages a team of helpdesk workers.", "database administrator", "project manager", "systems analyst", "helpdesk supervisor", "helpdesk supervisor"));

        //c13
        arrayList.add(new TQuestions("To install this software, he", "needs a product key.", "is an audio device.", "boots from the optical drive.", "turns off the computer.", "needs a product key."));
        //c14
        arrayList.add(new TQuestions("A pair of headphones", "needs a product key.", "is an audio device.", "boots from the optical drive.", "turns off the computer.", "is an audio device."));
        //c15
        arrayList.add(new TQuestions("This switch", "needs a product key.", "is an audio device.", "boots from the optical drive.", "turns off the computer.", "turns off the computer."));
        //c16
        arrayList.add(new TQuestions("This computer", "needs a product key.", "is an audio device.", "boots from the optical drive.", "turns off the computer.", "boots from the optical drive."));
        //c17
        arrayList.add(new TQuestions("A projector", "is for showing images and video.", "has three tabs.", "connects to the motherboard and stores data.", "often connects to the computer using a USB port.", "is for showing images and video."));
        //c18
        arrayList.add(new TQuestions("This window", "is for showing images and video.", "has three tabs.", "connects to the motherboard and stores data.", "often connects to the computer using a USB port.", "has three tabs."));
        //c19
        arrayList.add(new TQuestions("A hard drive", "is for showing images and video.", "has three tabs.", "connects to the motherboard and stores data.", "often connects to the computer using a USB port.", "is for showing images and video."));
        //c20
        arrayList.add(new TQuestions("An external drive", "is for showing images and video.", "has three tabs.", "connects to the motherboard and stores data.", "often connects to the computer using a USB port.", "is for showing images and video."));
        //c21
        arrayList.add(new TQuestions("I back up my data ________ security.", "to", "for", "so", "because", "for"));
        //c22
        arrayList.add(new TQuestions("I use open source software ________ it’s free.", "to", "for", "so", "because", "because"));
        //c23
        arrayList.add(new TQuestions("You can double click on the title bar ________ maximise the window.", "to", "for", "so", "because", "to"));
        //c24
        arrayList.add(new TQuestions("I use a video camera ________ that I can show video to people.", "to", "for", "so", "because", "so"));
        //c25
        arrayList.add(new TQuestions("You can use an external hard drive ________ back up your data.", "to", "for", "so", "because", "to"));
        //c26
        arrayList.add(new TQuestions("Drag the folder icon ________ move it to a new drive.", "to", "for", "so", "because", "to"));
        //c27
        arrayList.add(new TQuestions("I bought a webcam ________ that I can make video calls.", "to", "for", "so", "because", "so"));

        //c28
        arrayList.add(new TQuestions("a wireless ________", "network ", "address ", "password ", "link ", "network "));
        //c29
        arrayList.add(new TQuestions("a web ________", "network ", "address ", "password ", "link ", "address "));
        //c30
        arrayList.add(new TQuestions("enter a(n) ________", "network ", "address ", "password ", "link ", "password "));
        //c31
        arrayList.add(new TQuestions("follow a(n) ________", "network ", "address ", "password ", "link ", "link "));

        //c32
        arrayList.add(new TQuestions("browse ________", "websites ", "address ", "life ", "button", "websites "));
        //c33
        arrayList.add(new TQuestions("the recipient’s ________", "websites ", "address ", "life ", "button", "address "));
        //c34
        arrayList.add(new TQuestions("short battery ________", "websites ", "address ", "life ", "button", "life "));
        //c35
        arrayList.add(new TQuestions("the refresh ________", "websites ", "address ", "life ", "button", "button"));

        //c3
        arrayList.add(new TQuestions("", "", "", "", "", ""));
        //c3
        arrayList.add(new TQuestions("", "", "", "", "", ""));

    }
}
