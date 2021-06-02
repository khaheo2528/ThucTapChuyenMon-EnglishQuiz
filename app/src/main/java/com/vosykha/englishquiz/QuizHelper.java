package com.vosykha.englishquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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


        //unit 4
        //c36
        arrayList.add(new TQuestions("type ________ a formula", "in", "record", "by", "key", "in"));
        //c37
        arrayList.add(new TQuestions("retrieve a(n) ________", "in", "record", "by", "key", "record"));
        //c38
        arrayList.add(new TQuestions("multiply ________", "in", "record", "by", "key", "by"));
        //c39
        arrayList.add(new TQuestions("primary ________", "in", "record", "by", "key", "key"));
        //c40
        arrayList.add(new TQuestions("query a(n) ________", "database", "permissions", "smoothly", "tablet", "database"));
        //c41
        arrayList.add(new TQuestions("set ________", "database", "permissions", "smoothly", "tablet", "permissions"));
        //c42
        arrayList.add(new TQuestions("run ________", "database", "permissions", "smoothly", "tablet", "smoothly"));
        //c43
        arrayList.add(new TQuestions("graphics ________", "database", "permissions", "smoothly", "tablet", "tablet"));
        //c44
        arrayList.add(new TQuestions("something you put on your head to listen and speak to other people over the internet ________", "headset", "jam", "minus", "form ", "headset"));
        //c44
        arrayList.add(new TQuestions("a problem that happens when paper gets stuck in a printer ________", "headset", "jam", "minus", "form ", "jam"));
        //c44
        arrayList.add(new TQuestions("a word for the ‘–’ symbol in formulae ________", "headset", "jam", "minus", "form ", "minus"));
        //c44
        arrayList.add(new TQuestions("a database object that makes it easy to enter data ________", "headset", "jam", "minus", "form ", "form"));
        //c45
        arrayList.add(new TQuestions("a line of cells from top to bottom of a worksheet in a spreadsheet ________", "column", "deploy", "divide", "multifunction printer", "column"));
        //c46
        arrayList.add(new TQuestions("remotely install new software on a group of computers ________", "column", "deploy", "divide", "multifunction printer", "deploy"));
        //c47
        arrayList.add(new TQuestions("a word for the ‘/’ symbol in formulae ________", "column", "deploy", "divide", "multifunction printer", "divide"));
        //c48
        arrayList.add(new TQuestions("a device that prints, scans and copies ________", "column", "deploy", "divide", "multifunction printer", "multifunction printer"));

        //unit 5
        //c49
        arrayList.add(new TQuestions("shared ________", "hosting ", "fee ", "warranty ", "pricing ", "hosting "));
        //c50
        arrayList.add(new TQuestions("monthly ________", "hosting ", "fee ", "warranty ", "pricing ", "fee "));
        //c51
        arrayList.add(new TQuestions("extended ________", "hosting ", "fee ", "warranty ", "pricing ", "warranty "));
        //c52
        arrayList.add(new TQuestions("traditional ________", "hosting ", "fee ", "warranty ", "pricing ", "pricing "));
        //c53
        arrayList.add(new TQuestions("user ________", "guide ", "address ", "reader ", "compatibility", "guide "));
        //c54
        arrayList.add(new TQuestions("IP ________", "guide ", "address ", "reader ", "compatibility", "address "));
        //c55
        arrayList.add(new TQuestions("card ________", "guide ", "address ", "reader ", "compatibility", "reader "));
        //c56
        arrayList.add(new TQuestions("file ________", "guide ", "address ", "reader ", "compatibility", "compatibility"));
        //c57
        arrayList.add(new TQuestions("The freemium plan is ________ expensive than the premium plan.", "less", "to", "were", "what", "less"));
        //c58
        arrayList.add(new TQuestions("I think it’s best ________ buy more computers. Repairing old ones won’t help.", "less", "to", "were", "what", "to"));
        //c59
        arrayList.add(new TQuestions("You bought some batteries? How much ________ they in total?", "less", "to", "were", "what", "were"));
        //c60
        arrayList.add(new TQuestions("Do you know ________ the cost is?", "less", "to", "were", "what", "what"));
        //đáp án có thể sai :3 mong người dùng thông cảm
        //unit 6
        //c61
        arrayList.add(new TQuestions("As well as our online stores, we also still have a few bricks and ________ shops.", "mortar", "encryption", "document", "remote ", "mortar"));
        //c62
        arrayList.add(new TQuestions("For security, I think we need ________ of all our messages so that no one else can read them.", "mortar", "encryption", "document", "remote ", "encryption"));
        //c63
        arrayList.add(new TQuestions("I think we need a(n) ________ management system to handle all our scanned paperwork and word processed files.", "mortar", "encryption", "document", "remote ", "document"));
        //c64
        arrayList.add(new TQuestions("In our video conference, the ________ participants will be speaking with us from two overseas countries.", "mortar", "encryption", "document", "remote ", "remote"));
        //c65
        arrayList.add(new TQuestions("Our system needs speech-to-________ capability so that we can write emails and messages by speaking into a microphone.", "text ", "compression ", "integrate ", "field", "text "));
        //c66
        arrayList.add(new TQuestions("We can save bandwidth by using ________. That way, the data we send is smaller and we don’t lose any information.", "text ", "compression ", "integrate ", "field", "compression "));
        //c67
        arrayList.add(new TQuestions("When we design an e-commerce system, we have to ________ several different components so that they work together well.", "text ", "compression ", "integrate ", "field", "integrate "));
        //c68
        arrayList.add(new TQuestions("You can pay using our near ________ communication (NFC) system.", "text ", "compression ", "integrate ", "field", "field"));
        //c69
        arrayList.add(new TQuestions("a small bit of data that websites put on your computer ________","cookie", "local participants", "microblogging", "tagging","cookie"));
        //c70
        arrayList.add(new TQuestions("the people in the same place as you in a video conferencing set-up ________","cookie", "local participants", "microblogging", "tagging","local participants"));
        //c71
        arrayList.add(new TQuestions("a system for sending only very short messages from computers ________","cookie", "local participants", "microblogging", "tagging","microblogging"));
        //c72
        arrayList.add(new TQuestions("adding labels to files such as photos so that they are easy to find in a search ________","cookie", "local participants", "microblogging", "tagging","tagging"));
        //c73
        arrayList.add(new TQuestions("a standard way of doing something ________","cookie", "method", "premises ", "component","method"));
        //c74
        arrayList.add(new TQuestions("the building that a company uses ________","procedure", "method", "premises ", "tagging","premises "));
        //c75
        arrayList.add(new TQuestions("a small part of a bigger system ________","procedure", "method", "microblogging", "component","component"));

        this.addAllQuestions(arrayList);
    }

    private void addAllQuestions(ArrayList<TQuestions> AllQuestions){
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (TQuestions question: AllQuestions){
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOptA());
                values.put(OPTB, question.getOptB());
                values.put(OPTC, question.getOptC());
                values.put(OPTD, question.getOptD());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    List<TQuestions> getAllOfTheQuestions(){
        List<TQuestions> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);

        while (cursor.moveToNext()){
            TQuestions question = new TQuestions();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(2));
            question.setOptB(cursor.getString(3));
            question.setOptC(cursor.getString(4));
            question.setOptD(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionsList.add(question);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }
}
