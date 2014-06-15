package com.example.twisted_hangman;

import com.example.twisted_hangman.sqlite.User;
import com.example.twisted_hangman.sqlite.helper.DatabaseHelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;


public class newuserActivity extends ActionBarActivity {
	
	Button go;
	EditText name, difficulty, amount_of_letters;
	DatabaseHelper db;
	Switch type;
	String gametype;
	User user;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newuser);
		addListenerOnButtonGo();
		
		gametype = "evil";
		type = (Switch) findViewById(R.id.type);
		
		if (type != null) {
			type.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					gametype = "evil";
					if(isChecked){
						gametype = "normal";
					}
				}
			});
		}
			
		db = new DatabaseHelper(getApplicationContext(), "hangman", null, 2);
	}
	
	public void addListenerOnButtonGo() {
		final Context context = this;
		go = (Button) findViewById(R.id.go);
		name = (EditText)findViewById(R.id.editName);
		difficulty = (EditText)findViewById(R.id.editDifficulty);
		type = (Switch)findViewById(R.id.type);
		amount_of_letters = (EditText)findViewById(R.id.amountOfLetters);

		go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				db.createUser(name.getText().toString(), Integer.parseInt(difficulty.getText().toString()), 
						gametype, Integer.parseInt(amount_of_letters.getText().toString()), 0.0);
				user = db.getUserByName(name.getText().toString());
				System.out.println(user.getName() + " " + user.getGameType());
			    Intent intent = new Intent(context, singleplayerActivity.class);
			    Bundle b = new Bundle();
			    b.putInt("id", user.getID());
			    intent.putExtras(b);
                startActivity(intent);   
 
			}
 
		});
 
	}
}
