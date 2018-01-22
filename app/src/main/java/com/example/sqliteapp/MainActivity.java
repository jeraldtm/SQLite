package com.example.sqliteapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.List;

public class MainActivity extends Activity {
    MySQLiteHelper db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MySQLiteHelper(this);
    }

    public void onClickAdd(View view){
        String name = ((EditText)findViewById(R.id.editText)).getText().toString();
        String rating = ((EditText)findViewById(R.id.editText2)).getText().toString();
        db.addGame(new Game(name, rating));
        db.getAllGames();
    }

    public void onClickListAll(View view){
        db.getAllGames();
    }

    public void onClickDelete(View view){
        int id = Integer.parseInt(((EditText)findViewById(R.id.editText)).getText().toString());
        db.deleteGame(db.getGame(id));
    }

    public void onClickList(View view){
        int id = Integer.parseInt(((EditText)findViewById(R.id.editText)).getText().toString());
        db.getGame(id);
    }

    public void onClickDeleteAll(View view){
        db.deleteAll();
    }



}
