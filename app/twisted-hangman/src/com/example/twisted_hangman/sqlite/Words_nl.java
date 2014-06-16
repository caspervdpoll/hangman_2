package com.example.twisted_hangman.sqlite;

/* Class to represent the words from database */
public class Words_nl {
	int _id;
    String _value;

     
    // Empty constructor
    public Words_nl(){
         
    }
    // constructor
    public Words_nl(int id, String value){
        this._id = id;
        this._value = value;
    }
     
    // constructor
    public Words_nl(String value){
    	this._value = value;
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
}
