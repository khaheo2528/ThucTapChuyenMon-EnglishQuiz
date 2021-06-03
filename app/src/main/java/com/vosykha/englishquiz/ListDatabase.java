package com.vosykha.englishquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.vosykha.englishquiz.add_edit.AddActivityProduct;
import com.vosykha.englishquiz.add_edit.EditActivityProduct;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

public class ListDatabase extends AppCompatActivity {

    private static final String DB_NAME ="TQuiz.db";
    public static final String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;

    ListView LvDatabase;
    ArrayAdapter<TQuestions> adapter;

    DecimalFormat currency = new DecimalFormat("###.###");

    public static TQuestions selectedTQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_database);

        copyDataBase();
        addViews();
        addEvents();

    }

    private void addEvents() {
        LvDatabase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTQuestions = adapter.getItem(position);
            }
        });
        LvDatabase.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTQuestions = adapter.getItem(position);
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataFromData();
    }

    private void loadDataFromData() {
        database = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        adapter.clear();
        Cursor cursor = database.query("TQ", null,null, null, null, null, null);
        int id;
        String question;
        String opta;
        String optb;
        String optc;
        String optd;
        String answer;
        while (cursor.moveToNext()){
            id = cursor.getInt(0);
            question = cursor.getString(1);
            opta = cursor.getString(2);
            optb = cursor.getString(3);
            optc = cursor.getString(4);
            optd = cursor.getString(5);
            answer = cursor.getString(6);
            TQuestions tQuestions = new TQuestions(id,question,opta,optb,optc,optd,answer);
            adapter.add(tQuestions);
        }
        cursor.close();
    }

    private void copyDataBase() {
        try {
            File dbFile = getDatabasePath(DB_NAME);
            if(!dbFile.exists()){
                if(copyDBFromAsset())
                    Toast.makeText(ListDatabase.this,
                            "Copy database successful", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(ListDatabase.this,
                        "Copy database fail!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){
            Log.e("Error", e.toString());
        }
    }

    private boolean copyDBFromAsset() {
        String dbPath = getApplicationInfo().dataDir + DB_PATH_SUFFIX + DB_NAME;
        try {
            InputStream inputStream = getAssets().open(DB_NAME);
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()){
                f.mkdir();
            }
            OutputStream outputStream = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length=inputStream.read(buffer))>0){
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return true;
        } catch (IOException e ){
            e.printStackTrace();
            return false;
        }
    }

    private void addViews() {
        LvDatabase = (ListView) findViewById(R.id.lvDatabase);
        adapter = new ArrayAdapter<TQuestions>(ListDatabase.this, android.R.layout.simple_list_item_1);
        LvDatabase.setAdapter(adapter);

        registerForContextMenu(LvDatabase);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnEdit){
            if(selectedTQuestions != null){
                Intent intent = new Intent(ListDatabase.this, EditActivityProduct.class);
                startActivity(intent);
            }
        }
        else if(item.getItemId() == R.id.mnDelete){
            if(selectedTQuestions != null){
                AlertDialog.Builder builder = new AlertDialog.Builder(ListDatabase.this);
                builder.setTitle("Confirm delete question");
                builder.setMessage("Are you sure that you want to delete this question: " +selectedTQuestions + "");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int flag = database.delete("TQ ", "question = ?",
                                new String[]{ selectedTQuestions.getQuestion() + ""});
                        if (flag> 0)
                            loadDataFromData();
                        else
                            Toast.makeText(ListDatabase.this, "Delete Fail", Toast.LENGTH_LONG).show();
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        }
        else if (item.getItemId()==R.id.mnAddQuestion){
            if ((selectedTQuestions != null)) {
                Intent intent = new Intent(ListDatabase.this, AddActivityProduct.class);
                startActivity(intent);
            }
        }
        return super.onContextItemSelected(item);
    }

}


