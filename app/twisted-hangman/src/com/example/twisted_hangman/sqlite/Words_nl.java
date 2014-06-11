package com.example.twisted_hangman.sqlite;

public class Words_nl {
	int _id;
    String _value;
    int _letter_count;

     
    // Empty constructor
    public Words_nl(){
         
    }
    // constructor
    public Words_nl(int id, String value, int letter_count){
        this._id = id;
        this._value = value;
        this._letter_count = letter_count;
    }
     
    // constructor
    public Words_nl(String value, int letter_count){
    	this._value = value;
        this._letter_count = letter_count;
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
    public String getValue(){
        return this._value;
    }
     
    // setting name
    public void setValue(String value){
        this._value = value;
    }
     
    // getting difficulty
    public int getLetterCount(){    	
        return this._letter_count;
    }
     
    // setting difficulty
    public void setLetterCount(int letter_count){
        this._letter_count = letter_count;
    }
}
