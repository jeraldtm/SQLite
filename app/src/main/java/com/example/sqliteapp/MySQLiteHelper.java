package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "GamesDB";
    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Games table name
    private static final String TABLE_GAMES = "games";

    //Games table columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_RATING = "rating";
    private static final String[] COLUMNS = {KEY_ID, KEY_NAME, KEY_RATING};

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GAME_TABLE = "CREATE TABLE " + TABLE_GAMES + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT," + KEY_RATING + " Text" + ");";

        db.execSQL(CREATE_GAME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS games");
        this.onCreate(db);
    }

    public void addGame (Game game){
        Log.d("addGame", game.toString());

        //Reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, game.getName());
        values.put(KEY_RATING, game.getRating());

        //Insert and close
        db.insert(TABLE_GAMES, null, values);
        db.close();
    }

    public Game getGame(int id) {
        //Reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        //Build query
        Cursor c = db.query(TABLE_GAMES, COLUMNS, "_id = ?", new String[]{String.valueOf(id)},
        null, null, null, null);

        //If got results get first entry
        if (c != null)
            c.moveToFirst();

        //Build game object
        Game game = new Game();
        game.setId(Integer.parseInt(c.getString(0)));
        game.setName(c.getString(1));
        game.setRating(c.getString(2));

        Log.d("getGame("+id+")", game.toString());

        return game;
    }

    public List<Game> getAllGames(){
        List<Game> games = new LinkedList<Game>();

        //Build query
        String query = "SELECT * FROM " + TABLE_GAMES;

        //Get reference to writable db
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        //Go over each row, build game and add to list
        Game game = null;
        if (c.moveToFirst()){
            do{
                game = new Game();
                game.setId(Integer.parseInt(c.getString(0)));
                game.setName(c.getString(1));
                game.setRating(c.getString(2));
                games.add(game);
            } while (c.moveToNext());
        }

        Log.d("getAllGames()", games.toString());

        return games;
    }

    public int updateGame(Game game){
        SQLiteDatabase db = getWritableDatabase();

        //Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name", game.getName());
        values.put("rating", game.getRating());

        //update row
        int i = db.update(TABLE_GAMES, values, KEY_ID+" = ?", new String[] {String.valueOf(game.getId())});

        db.close();

        return i;
    }

    public void deleteGame(Game game){
        //get reference to writable DB
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_GAMES, KEY_ID+" = ?", new String[]{String.valueOf(game.getId())});

        db.close();

        Log.d("deleteGame", game.toString());
    }

    public void deleteAll(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_GAMES, null, null);
        db.close();
        Log.d("deleteAll", "deleteAll");
    }
}
