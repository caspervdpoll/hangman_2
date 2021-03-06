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
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

/* Options can be changed in this activity */
public class optionsActivity extends ActionBarActivity {
	DatabaseHelper db;
	Bundle b;
	User user;
	Button go;
	EditText difficulty, name;
	SeekBar amount_of_letters;
	Switch type;
	String gametype;
	SeekBar seekbar, seekbar1;
	TextView value, value1;
	int diff, count, diff_option, letters_option;
	Boolean checked;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		// Get the argument given from MainActivity
		b = getIntent().getExtras();
		db = new DatabaseHelper(getApplicationContext(), "hangman", null, 2);

		user = db.getUserById(b.getInt("id"));

		addListenerOnButtonGo();
		
				
		gametype = user.getGameType();
		diff_option = user.getDifficulty();
		letters_option = user.getWordLength();		
		
		checked = gametype.equals("normal") ? false : true;
		type = (Switch) findViewById(R.id.type_options);
		type.setChecked(checked);
		
		if (type != null) {
			type.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					gametype = "normal";
					if(isChecked){
						gametype = "evil";
					}
					System.out.println(gametype);
				}
			});
		}
		
		// Set difficulty and wordlength
		value = (TextView) findViewById(R.id.TextView01_options);
		seekbar = (SeekBar) findViewById(R.id.editDifficulty_options);
		seekbar.setProgress(diff_option);
		value.setText("Difficulty: " + diff_option);
		        
		value1 = (TextView) findViewById(R.id.letter_amount_input_options);
		seekbar1 = (SeekBar) findViewById(R.id.editLetterAmount_options);
		seekbar1.setProgress(letters_option);
		value1.setText("Letter Count: " + letters_option);
		seekbar.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
		{
			public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser)
			{
                    // TODO Auto-generated method stub
				
					diff_option = (int)progress/10;
                    value.setText("Difficulty: " + diff_option);
			}

			public void onStartTrackingTouch(SeekBar seekBar)
			{
                    // TODO Auto-generated method stub
			}

			public void onStopTrackingTouch(SeekBar seekBar)
			{
                    // TODO Auto-generated method stub
			}

		});
		
		seekbar1.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
		{
			public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser)
			{
                    // TODO Auto-generated method stub
				
					letters_option = (int)progress;
                    value1.setText("Amount of letters: " + letters_option);
			}

			public void onStartTrackingTouch(SeekBar seekBar)
			{
                    // TODO Auto-generated method stub
			}

			public void onStopTrackingTouch(SeekBar seekBar)
			{
                    // TODO Auto-generated method stub
			}

		});
		
		db = new DatabaseHelper(getApplicationContext(), "hangman", null, 2);
	}
	
	public void addListenerOnButtonGo() {
		
		final Context context = this;		
		
		go = (Button) findViewById(R.id.go_options);	
		go.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				user.setWordLength(letters_option);
				user.setGameType(gametype);
				user.setDifficulty(diff_option);
				db.updateUser(user, b.getInt("id"));
				
			    Intent intent = new Intent(context, MainActivity.class);
 
			    intent.putExtras(b);
                startActivity(intent);   
 
			}
 
		});
 
	}
		
}

