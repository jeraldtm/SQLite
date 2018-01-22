package com.example.sqliteapp;

/**
 * Created by jerald on 1/21/18.
 */

public class Game {
    public int id;
    public String name;
    public String rating;

    public Game(){}

    public Game(String name, String rating){
        super();
        this.name = name;
        this.rating = rating;
    }

    public String toString(){
        return "com.example.sqliteapp.Game [id=" + id + ", name=" + name + ", rating=" +rating+ "]";
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getRating(){
        return rating;
    }

    public void setId(int readId){
        id = readId;
    }

    public void setName(String readName){
        name = readName;
    }

    public void setRating(String readRating){
        rating = readRating;
    }

}
