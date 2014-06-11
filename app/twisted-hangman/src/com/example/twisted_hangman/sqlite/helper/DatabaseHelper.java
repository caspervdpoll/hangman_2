package com.example.twisted_hangman.sqlite.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import com.example.twisted_hangman.sqlite.User;
import com.example.twisted_hangman.sqlite.Words_nl;

import android.content.ContentValues;
//import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	 // Logcat tag
    //private static final String LOG = "DatabaseHelper";
 
	
	private SQLiteDatabase myDataBase;
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "hangmanDatabase.db";
 
    // Table Names
    private static final String TABLE_USER = "users";
    private static final String TABLE_STATISTICS = "statistics";
    private static final String TABLE_WORDS_NL = "words_nl";
	
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(id INTEGER PRIMARY KEY, name TEXT, difficulty INTEGER, language TEXT, word_length INTEGER)";
 
    private static final String CREATE_TABLE_STATISTICS = "CREATE TABLE "
    		+ TABLE_STATISTICS + "(id INTEGER PRIMARY KEY, played INTEGER, lost INTEGER, won INTEGER, difficulty INTEGER, user_id INTEGER)";
 
    private static final String CREATE_TABLE_WORDS_NL = "CREATE TABLE "
            + TABLE_WORDS_NL + "(id INTEGER PRIMARY KEY, value TEXT, letter_count INTEGER)";
    
	public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_STATISTICS);
        db.execSQL(CREATE_TABLE_WORDS_NL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTICS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS_NL);
 
        // create new tables
        onCreate(db);
		
	}
	
	public void fillWords() {
		System.out.println("FILLING DB");
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_WORDS_NL, null);
        System.out.println("Cur " + cur);
        if (cur != null) {
            cur.moveToFirst();
            if (cur.getInt (0) == 0) {
            	System.out.println("YOLO2");
            	try { 
	            	File words = new File("words.xml");
	            	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            	DocumentBuilder document = dbf.newDocumentBuilder();
	            	Document doc = document.parse(words);
	            	doc.getDocumentElement().normalize();
	            	System.out.println("Root element " + doc.getDocumentElement().getNodeName());
	            	NodeList nodeLst = doc.getElementsByTagName("item");
	            	for(int i = 0; i < nodeLst.getLength(); i++) {
	            		Node tmp = nodeLst.item(i);
	            		System.out.println(tmp.toString());
	            	}
            	} catch(Exception e){}
            }
        }
	}
	/*
	 * fetch all words
	 */
	public List<Words_nl> getAllWords() { 
        List<Words_nl> wordList = new ArrayList<Words_nl>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_WORDS_NL;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) {
            do {
                Words_nl word = new Words_nl();
                word.setID(Integer.parseInt(cursor.getString(0)));
                word.setValue(cursor.getString(1));
                word.setLetterCount(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                wordList.add(word);
            } while (cursor.moveToNext());
        }
        
        return wordList;
	}
	
	public List<User> getAllUsers() { 
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setID(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setDifficulty(Integer.parseInt(cursor.getString(2)));
                user.setLanguage(cursor.getString(3));
                user.setWordLength(Integer.parseInt(cursor.getString(4)));
                // Adding contact to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        
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
	 
	    // insert row
	    long tag_id = db.insert(TABLE_WORDS_NL, null, values);

	    return tag_id;
	}
	
	public long createUser(String name, int difficulty, String language, int word_length){
		SQLiteDatabase db = this.getWritableDatabase();		 
	    ContentValues values = new ContentValues();
	    
	    values.put("name", name);
	    values.put("difficulty", difficulty);
	    values.put("language", language);
	    if(word_length != 0){
	    	values.put("word_length", word_length);
	    } else {
	    	values.put("word_length", 6);
	    }
	 
	    // insert row
	    long tag_id = db.insert(TABLE_USER, null, values);
	    System.out.println(tag_id);
	    return tag_id;
	}
	
	/*
	 * fetch word by id
	 */
	
	public String getWordById(int id){
		
		String Query = "SELECT * FROM " + TABLE_WORDS_NL + " WHERE id = '" + id + "'";
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
		
		String Query = "SELECT * FROM " + TABLE_USER + " WHERE name = '" + name + "'";
		User user = new User();
		int temp = 0;
		SQLiteDatabase db = this.getReadableDatabase();
	    Cursor c = db.rawQuery(Query, null);

	    if (c.moveToFirst()) {
            do {            	 
            	 temp = Integer.parseInt(c.getString(0));
                 user.setID(temp);
                 user.setName(c.getString(1));
                 user.setDifficulty(Integer.parseInt(c.getString(2)));
                 user.setLanguage(c.getString(3));
                 user.setWordLength(Integer.parseInt(c.getString(4)));              
            } while (c.moveToNext());
        }
	    System.out.println(temp);
		return user;
	}

	/*public void openDataBase() throws SQLException{
		 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    }*/
	
}
