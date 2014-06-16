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
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;


public class newuserActivity extends ActionBarActivity {
	
	Button go;
	EditText name, difficulty;
	SeekBar amount_of_letters;
	DatabaseHelper db;
	Switch type;
	String gametype;
	User user;
	SeekBar seekbar, seekbar1;
	TextView value, value1;
	int diff, count;
	
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
		
		value = (TextView) findViewById(R.id.TextView01);
		seekbar = (SeekBar) findViewById(R.id.editDifficulty);
		        
		value1 = (TextView) findViewById(R.id.letter_amount_input);
		seekbar1 = (SeekBar) findViewById(R.id.editLetterAmount);
		seekbar.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
		{
			public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser)
			{
                    // TODO Auto-generated method stub
				
					diff = (int)progress/10;
                    value.setText("Difficulty: " + diff);
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
				
					count = (int)progress;
                    value1.setText("Amount of letters: " + count);
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
		go = (Button) findViewById(R.id.go);
		name = (EditText)findViewById(R.id.editName);

		type = (Switch)findViewById(R.id.type);
		amount_of_letters = (SeekBar)findViewById(R.id.editLetterAmount);

		go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				db.createUser(name.getText().toString(), diff, 
						gametype, count, 0.0);
				user = db.getUserByName(name.getText().toString());
				db.createStatistics(user.getID());
				System.out.println(user.getName() + " " + user.getGameType());
			    Intent intent = new Intent(context, MainActivity.class);
			    Bundle b = new Bundle();
			    b.putInt("id", user.getID());
			    intent.putExtras(b);
                startActivity(intent);   
 
			}
 
		});
 
	}
}
