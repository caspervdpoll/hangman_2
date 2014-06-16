package com.example.twisted_hangman;

import com.example.twisted_hangman.sqlite.Statistics;
import com.example.twisted_hangman.sqlite.User;
import com.example.twisted_hangman.sqlite.helper.DatabaseHelper;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class statisticsActivity extends ActionBarActivity {
	DatabaseHelper db;
	Bundle b;
	TextView played, won, lost, highscore;
	Statistics stats;
	User user;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		
		db = new DatabaseHelper(getApplicationContext(), "hangman", null, 2);
		b = getIntent().getExtras();
		user = db.getUserById(b.getInt("id"));
		stats = db.getStatistics(user.getID());
		
		played = (TextView) findViewById(R.id.played);
		played.setText("Played: " + stats.getPlayed());
		
		won = (TextView) findViewById(R.id.won);
		won.setText("Won: " + stats.getWon());
		
		lost = (TextView) findViewById(R.id.lost);
		lost.setText("Lost: " + stats.getLost());
		
		highscore = (TextView) findViewById(R.id.highscore);
		highscore.setText("Highscore: " + user.getHighscore());
	}
}
