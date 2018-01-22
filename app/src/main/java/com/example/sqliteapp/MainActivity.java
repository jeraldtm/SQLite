package com.example.sqliteapp;

import android.app.Activity;
import android.database.CursorIndexOutOfBoundsException;
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
        ((EditText)findViewById(R.id.editText)).setText("");
        ((EditText)findViewById(R.id.editText2)).setText("");
    }

    public void onClickListAll(View view){
        db.getAllGames();
    }

    public void onClickDelete(View view){
        int id;
        try{
            id = Integer.parseInt(((EditText)findViewById(R.id.editText)).getText().toString());
        } catch(NumberFormatException e){
            id = 0;
        }
        try {
            db.deleteGame(db.getGame(id));
        } catch (CursorIndexOutOfBoundsException e2){
            ((EditText)findViewById(R.id.editText)).setText("");
            ((EditText)findViewById(R.id.editText)).setHint("Please enter a valid id");
        }
    }

    public void onClickList(View view){
        int id;
        try{
            id = Integer.parseInt(((EditText)findViewById(R.id.editText)).getText().toString());
        } catch(NumberFormatException e){
            id = 0;
        }
        try {
            Game game = db.getGame(id);
            ((EditText)findViewById(R.id.editText)).setText(game.toString());
        } catch (CursorIndexOutOfBoundsException e2){
            ((EditText)findViewById(R.id.editText)).setText("");
            ((EditText)findViewById(R.id.editText)).setHint("Please enter a valid id");
        }
    }

    public void onClickDeleteAll(View view){
        db.deleteAll();
    }
}
