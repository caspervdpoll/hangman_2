package com.example.twisted_hangman.sqlite.helper;

import com.example.twisted_hangman.sqlite.Words_nl;

//import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	 // Logcat tag
    //private static final String LOG = "DatabaseHelper";
 
    // Database Version
    //private static final int DATABASE_VERSION = 1;
 
    // Database Name
    //private static final String DATABASE_NAME = "hangmanDatabase";
 
    // Table Names
    private static final String TABLE_USER = "users";
    private static final String TABLE_STATISTICS = "statistics";
    private static final String TABLE_WORDS_NL = "words_nl";
	
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(id INTEGER PRIMARY KEY, name TEXT, difficulty TEXT, language TEXT, word_length INTEGER)";
 
    private static final String CREATE_TABLE_STATISTICS = "CREATE TABLE "
    		+ TABLE_STATISTICS + "(id INTEGER PRIMARY KEY, played INTEGER, lost INTEGER, won INTEGER, difficulty INTEGER, user_id INTEGER)";
 
    private static final String CREATE_TABLE_WORDS_NL = "CREATE TABLE "
            + TABLE_WORDS_NL + "(id INTEGER PRIMARY KEY, value TEXT, letter_count INTEGER)";
    
    
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
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
	
	/*
	 * Creating tag
	 */
	public long createTag(Words_nl word) {
	    //SQLiteDatabase db = this.getWritableDatabase();
	 
	    //ContentValues values = new ContentValues();
	    System.out.println(word);
	    //values.put("value", word);
	    //values.put(KEY_CREATED_AT, getDateTime());
	 
	    // insert row
	    //long tag_id = db.insert(TABLE_TAG, null, values);
	    int id = 1;
	    return id;
	}
}
