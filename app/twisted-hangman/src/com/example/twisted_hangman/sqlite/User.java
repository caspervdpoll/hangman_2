package com.example.twisted_hangman.sqlite;

public class User {
	
	int _id;
    String _name;
    int _difficulty;
    int _word_length;

    String _game_type;

     
    // Empty constructor
    public User(){
         
    }
    // constructor
    public User(int id, String name, int difficulty, String game_type, int word_length){
        this._id = id;
        this._name = name;
        this._difficulty = difficulty;
        this._game_type = game_type;
        this._word_length = word_length;
    }
     
    // constructor
    public User(String name, String game_type, int difficulty, int word_length){
    	 this._name = name;
         this._difficulty = difficulty;
         this._game_type = game_type;
         this._word_length = word_length;
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
    
    public int getWordLength(){
    	return this._word_length;
    }
    
    public void setWordLength(int word_length){
    	this._word_length = word_length;
    }
}
