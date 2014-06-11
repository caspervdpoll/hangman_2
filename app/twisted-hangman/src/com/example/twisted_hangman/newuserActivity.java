package com.example.twisted_hangman;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class newuserActivity extends ActionBarActivity {
	
	Button go;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newuser);
		addListenerOnButtonGo();
	}
	
	public void addListenerOnButtonGo() {
		final Context context = this;
		go = (Button) findViewById(R.id.go);
		go.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, singleplayerActivity.class);
                            startActivity(intent);   
 
			}
 
		});
 
	}
}
