package com.example.twisted_hangman.sqlite;

public class Statistics {
	int _id;
    int _played;
    int _won;
    int _lost;
    int _user_id;

     
    // Empty constructor
    public Statistics(){
         
    }
    // constructor
    public Statistics(int id, int played, int won, int lost, int user_id){
        this._id = id;
        this._played = played;
        this._won = won;
        this._lost = lost;
        this._user_id = user_id;
    }
     
    // constructor
    public Statistics(int played, int won, int lost, int user_id){
        this._played = played;
        this._won = won;
        this._lost = lost;
        this._user_id = user_id;
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
    public int getPlayed(){
        return this._played;
    }
     
    // setting name
    public void setPlayed(int played){
        this._played = played;
    }
     
    // getting difficulty
    public int getWon(){    	
        return this._won;
    }
     
    // setting difficulty
    public void setWon(int won){
        this._won = won;
    }
    
    // getting difficulty
    public int getLost(){    	
        return this._lost;
    }
     
    // setting difficulty
    public void setLost(int lost){
        this._lost = lost;
    }
    
    // getting difficulty
    public int getUserId(){    	
        return this._user_id;
    }
     
    // setting difficulty
    public void setUserId(int user_id){
        this._user_id = user_id;
    }
}
