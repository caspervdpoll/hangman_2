package com.example.twisted_hangman.sqlite;

public class User {
public enum Language{nederlands, engels}
	
	int _id;
    String _name;
    int _difficulty;

    String _game_type;

     
    // Empty constructor
    public User(){
         
    }
    // constructor
    public User(int id, String name, int difficulty, String game_type){
        this._id = id;
        this._name = name;
        this._difficulty = difficulty;
        this._game_type = game_type;
    }
     
    // constructor
    public User(String name, String game_type, int difficulty){
    	 this._name = name;
         this._difficulty = difficulty;
         this._game_type = game_type;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
     
    // getting name
    public String getName(){
        return this._name;
    }
     
    // setting name
    public void setName(String name){
        this._name = name;
    }
  
 // getting difficulty
    public int getDifficulty(){    	
        return this._difficulty;
    }
     
    // setting difficulty
    public void setDifficulty(int difficulty){
        this._difficulty = difficulty;
    }
    
    // getting difficulty
    public String getGameType(){    	
        return this._game_type;
    }
     
    // setting difficulty
    public void setGameType(String game_type){
        this._game_type = game_type;
    }
}
