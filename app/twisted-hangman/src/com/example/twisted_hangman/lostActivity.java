package com.example.twisted_hangman;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class lostActivity extends ActionBarActivity {
	Button back;
	Bundle b;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lost);
		addListenerOnButtonBack();
		b = getIntent().getExtras();
	}
	
	public void addListenerOnButtonBack() {
		final Context context = this;
		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context, MainActivity.class);
				intent.putExtras(b);
				startActivity(intent);
			}
		});
	}

}
