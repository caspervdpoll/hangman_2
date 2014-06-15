package com.example.twisted_hangman;

import com.example.twisted_hangman.sqlite.helper.DatabaseHelper;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class optionsActivity extends ActionBarActivity {
	DatabaseHelper db;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		db = new DatabaseHelper(getApplicationContext(), "hangman", null, 2);
	}
}
