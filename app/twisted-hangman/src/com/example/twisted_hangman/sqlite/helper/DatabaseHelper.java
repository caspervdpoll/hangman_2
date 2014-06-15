package com.example.twisted_hangman.sqlite.helper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import com.example.twisted_hangman.sqlite.User;
import com.example.twisted_hangman.sqlite.Words_nl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.example.twisted_hangman/databases/";
    private static String DB_NAME = "hangman";
    private SQLiteDatabase myDataBase; 
    private final Context myContext;
    
    public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		this.myContext = context;
	}
	  /**
	     * Creates a empty database on the system and rewrites it with your own database.
	     * */
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
    	//copyDataBase(); //Uncomment this when the database already exists, but isn't filled
    	//System.out.println(dbExist);
    	if(dbExist){
    		//Database exists
    	} else {
        	try {
    			copyDataBase();
    		} catch (IOException e) {
        		throw new Error("Error copying database");
        	}
    	}
 
    }
	 
	    /**
	     * Check if the database already exist to avoid re-copying the file each time you open the application.
	     * @return true if it exists, false if it doesn't
	     */
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try {
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	} catch(SQLiteException e) {
    		e.printStackTrace();
    	}
 
    	if(checkDB != null)
    		checkDB.close();
 
    	return checkDB != null ? true : false;
    }
	 
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME + ".db");
    	
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
    	
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
    	
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 }
	 
    public void openDataBase() throws SQLException{
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }
	 
    @Override
	public synchronized void close() {
 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    super.close();
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
	 
	public ArrayList<String> getAllWords(int word_length) { 
        ArrayList<String> wordList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM WORDS_" + word_length;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) {
            do {
                Words_nl word = new Words_nl();
                word.setID(Integer.parseInt(cursor.getString(0)));
                word.setValue(cursor.getString(1));
                wordList.add(word.getValue());
            } while (cursor.moveToNext());
        }
        
        return wordList;
	}
	
	public ArrayList<User> getAllUsers() { 
        ArrayList<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT rowid, * FROM users";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor != null && cursor.getCount() >0 && cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setID(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(2));
                user.setDifficulty(Integer.parseInt(cursor.getString(3)));
                user.setGameType(cursor.getString(4));
                user.setHighscore(cursor.getDouble(5));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        System.out.println("na de query" + userList);
        return userList;
	}
	
	/*
	 * Creating wordtag
	 */
	public long createWord(String word) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    int length = word.length();
	    
	    values.put("value", word);
	    values.put("letter_count", length);
	 
	    return 1;
	}
	
	public long createUser(String name, int difficulty, String type, int amount_of_letters, double highscore){
		SQLiteDatabase db = this.getWritableDatabase();		 
	    ContentValues values = new ContentValues();
	    
	    values.put("name", name);
	    values.put("difficulty", difficulty);
	    values.put("type", type);
	    values.put("word_length", amount_of_letters);
	    values.put("highscore", highscore);
	 
	    
	    System.out.println(values);
	    // insert row
	    long tag_id = db.insert("users", null, values);
	   // System.out.println(tag_id);
	    return tag_id;
	}
	
	/*
	 * fetch word by id
	 */
	
	public String getWordById(int id){
		
		String Query = "SELECT * FROM " + " WHERE id = '" + id + "'";
		String temp = "";
		SQLiteDatabase db = this.getReadableDatabase();
	    Cursor c = db.rawQuery(Query, null);

	    if (c.moveToFirst()) {
            do {
                temp = c.getString(1);              
            } while (c.moveToNext());
        }

		return temp;
	}
	
	public User getUserByName(String name){
		int temp = 0;
		String Query = "SELECT rowid, * FROM users WHERE name = '" + name + "'";
		User user = new User();
		SQLiteDatabase db = this.getReadableDatabase();
	    Cursor c = db.rawQuery(Query, null);

	    if (c.moveToFirst()) {
            do {
            	 System.out.println(c.getString(0));
            	 temp = Integer.parseInt(c.getString(0));
                 user.setID(temp);
                 user.setName(c.getString(2));
                 user.setDifficulty(Integer.parseInt(c.getString(3)));
                 user.setGameType(c.getString(4));        
                 user.setHighscore(c.getDouble(5));
            } while (c.moveToNext());
        }
	    System.out.println(temp);
		return user;
	}
	
	public User getUserById(int id){
		int temp = 0;
		String Query = "SELECT * FROM users WHERE rowid = '" + id + "'";
		User user = new User();
		SQLiteDatabase db = this.getReadableDatabase();
	    Cursor c = db.rawQuery(Query, null);

	    if (c != null && c.getCount() >0 && c.moveToFirst()) {
            do {
                 user.setID(temp);
                 user.setName(c.getString(1));
                 user.setDifficulty(c.getInt(2));
                 user.setGameType(c.getString(3)); 
                 user.setWordLength(c.getInt(4));
                 user.setHighscore(c.getDouble(5));
            } while (c.moveToNext());
        }
	    
	    c.close();

		return user;
		
	}
}
