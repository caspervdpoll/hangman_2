package com.example.twisted_hangman.sqlite;

public class User {
public enum Language{nederlands, engels}
	
	int _id;
    String _name;
    int _difficulty;
    String _language;
    int _word_length;

     
    // Empty constructor
    public User(){
         
    }
    // constructor
    public User(int id, String name, int difficulty, String language, int word_length){
        this._id = id;
        this._name = name;
        this._difficulty = difficulty;
        this._language = language;
        this._word_length = word_length;
    }
     
    // constructor
    public User(String name, int difficulty, String language, int word_length){
    	 this._name = name;
         this._difficulty = difficulty;
         this._language = language;
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
    public String getLanguage(){    	
        return this._language;
    }
     
    // setting difficulty
    public void setLanguage(String language){
        this._language = language;
    }
    
    // getting difficulty
    public int getWordLength(){    	
        return this._word_length;
    }
     
    // setting difficulty
    public void setWordLength(int word_length){
        this._word_length = word_length;
    }
}
