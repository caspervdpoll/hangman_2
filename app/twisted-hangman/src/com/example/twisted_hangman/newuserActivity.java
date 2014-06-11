package com.example.twisted_hangman;

import com.example.twisted_hangman.sqlite.User;
import com.example.twisted_hangman.sqlite.helper.DatabaseHelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;


public class newuserActivity extends ActionBarActivity {
	
	Button go;
	EditText name, difficulty;
	Switch lang;
	DatabaseHelper db;
	String language;
	User user;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newuser);
		addListenerOnButtonGo();
		language = "nederlands";
		lang =  (Switch) findViewById(R.id.editLanguage);
		if (lang != null) {
			lang.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					language = "nederlands";
					if(isChecked){
						language = "engels";
					}
					System.out.println(language);
				}
			});
		}
			
		db = new DatabaseHelper(getApplicationContext());
	}
	
	public void addListenerOnButtonGo() {
		final Context context = this;
		go = (Button) findViewById(R.id.go);
		name = (EditText)findViewById(R.id.editName);
		difficulty = (EditText)findViewById(R.id.editDifficulty);
		go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				db.createUser(name.getText().toString(), Integer.parseInt(difficulty.getText().toString()), language, 0);
				user = db.getUserByName(name.getText().toString());
				System.out.println(user.getName() + " " + user.getLanguage());
			    Intent intent = new Intent(context, singleplayerActivity.class);
                startActivity(intent);   
 
			}
 
		});
 
	}
}
