package com.example.twisted_hangman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.example.twisted_hangman.sqlite.helper.DatabaseHelper;
import com.example.twisted_hangman.sqlite.User;
import com.example.twisted_hangman.sqlite.Words_nl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class singleplayerActivity extends ActionBarActivity {
	ArrayList<String> words;
	DatabaseHelper db;
	Button A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R_button, S, T, U, V, W, X, Y, Z;
	TextView word;
	ImageView hangman;
	int nrOfFaults;
	Bundle b;
	User user;
	String word_normal = "";
	String game_type = "";
	
    
   	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_singleplayer);
		
		db = new DatabaseHelper(getApplicationContext(), "hangman", null, 2);
		b = getIntent().getExtras();
		int letter_count = 5;
		int words_size;
		int word_id;
		
		Random randomGenerator = new Random();
		
		user = db.getUserById(b.getInt("id"));

		letter_count = user.getWordLength();
		game_type = user.getGameType();
		
		words = db.getAllWords(letter_count);

		if(game_type.equals("normal")){

			//random number generator
			words_size = words.size();
			word_id = randomGenerator.nextInt(40000) % words_size;
			
			word_normal = words.get(word_id);
		}
		
       
        A = (Button) findViewById(R.id.A);
        B = (Button) findViewById(R.id.B);
        C = (Button) findViewById(R.id.C);
        D = (Button) findViewById(R.id.D);
        E = (Button) findViewById(R.id.E);
        F = (Button) findViewById(R.id.F);
        G = (Button) findViewById(R.id.G);
        H = (Button) findViewById(R.id.H);
        I = (Button) findViewById(R.id.I);
        J = (Button) findViewById(R.id.J);
        K = (Button) findViewById(R.id.K);
        L = (Button) findViewById(R.id.L);
        M = (Button) findViewById(R.id.M);
        N = (Button) findViewById(R.id.N);
        O = (Button) findViewById(R.id.O);
        P = (Button) findViewById(R.id.P);
        Q = (Button) findViewById(R.id.Q);
        R_button = (Button) findViewById(R.id.R);
        S = (Button) findViewById(R.id.S);
        T = (Button) findViewById(R.id.T);
        U = (Button) findViewById(R.id.U);
        V = (Button) findViewById(R.id.V);
        W = (Button) findViewById(R.id.W);
        X = (Button) findViewById(R.id.X);
        Y = (Button) findViewById(R.id.Y);
        Z = (Button) findViewById(R.id.Z);
        word = (TextView) findViewById(R.id.word); 
        hangman = (ImageView) findViewById(R.id.hangman);
        
        String wordToPrint = ""; 
        for(int i = 0; i < letter_count ; i++)
        	wordToPrint += "_ ";
        
        word.setText(wordToPrint);
        nrOfFaults = 0;
	}
   	
   	public void onClick(View v) {

   		Button b = (Button)v;
   		String buttonText = b.getText().toString();
   		if(game_type.equals("evil"))
   			filterWords(words, buttonText.charAt(0), b);
   		else
   			filterWord(word_normal, buttonText.charAt(0), b);
   	}
   	
   	private void youWon() {
   		onBackPressed();
   	}
   	
   	public void updateHangman(int step) {
   		String filename = "step" + step;
   		if(step != 11) {
   			int resID = getResources().getIdentifier(filename, "drawable", getPackageName());
   			hangman.setImageResource(resID);
   		}
   	}
   	
   	public void filterWord(String word_temp, char input, Button button){
   		System.out.println(word_temp);
   		System.out.println(input);
   		String key = "";
   		if(word_temp.indexOf(input) >= 0){
   			
   			button.setTextColor(Color.GREEN);
   			button.setEnabled(false);
   			button.setBackground(this.getResources().getDrawable(R.drawable.roundedgray));
  			
			for(int j = 0; j < word_temp.length(); j++){
				char compare = word_temp.charAt(j);
				if(compare == input)
					key += "1";
				else
					key += "0";
			}
			
			String updatedWord = "";
	   		for(int i = 0; i < key.length(); i++) {
	   			char compare = key.charAt(i);
	   			if(compare == '1') {
	   				updatedWord += input + " ";
	   			} else {
	   				updatedWord += word.getText().charAt(i*2) + " ";
	   			}
	   		}
	   		
	   		word.setText(updatedWord);
	   		
	   		if(updatedWord.indexOf('_') < 0)
	   			youWon();
   		} else {
   			button.setTextColor(Color.RED);
   			button.setEnabled(false);
   			button.setBackground(this.getResources().getDrawable(R.drawable.roundedgray)); 
   			
   			if(nrOfFaults++ < 9)
   	   			updateHangman(nrOfFaults);
   	   		else 
   	   			onBackPressed();
   		}
   			
   		
   	}
   	
   	public ArrayList<String> filterWordsOnLength(ArrayList<String> words, int length) {
   		ArrayList<String> result = new ArrayList<String>();
   		
   		for(int i = 0; i < words.size(); i++) {
   			if(words.get(i).length() == length)
   				result.add(words.get(i));
   		}
   			
   		return result;
   	}
   	
   	public ArrayList<String> filterOnChar(ArrayList<String> words, char input) {
   		ArrayList<String> result = null;
   		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
   		int max = 0;
   		String maxKey = "";
   		if(words.size() > 1) {
	   		for(int i = 0; i < words.size(); i++){
	   			String key = "";
	   			for(int j = 0; j < words.get(i).length(); j++){
	   				char compare = words.get(i).charAt(j);
	   				if(compare == input)
	   					key += "1";
	   				else
	   					key += "0";
	   			}
	   			if(map.containsKey(key)){
	   				ArrayList<String> temp = map.get(key);
	   				temp.add(words.get(i));
	   	   			map.put(key, temp);
	   			} else {
	   				ArrayList<String> temp = new ArrayList<String>();
	   				map.put(key, temp);
	   			}
	   		}
   		} else {
   			String key = "";
   			for(int j = 0; j < words.get(0).length(); j++){
   				char compare = words.get(0).charAt(j);
   				if(compare == input)
   					key += "1";
   				else
   					key += "0";
   			}
   			map.put(key, words);
   		}
   		
  		
   		for(Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
   			if(map.get(entry.getKey()).size() > max) {
   				max = map.get(entry.getKey()).size();
   				maxKey = entry.getKey();
   				result = map.get(entry.getKey());
   			}
   		}
   		

   		
   		String updatedWord = "";
   		for(int i = 0; i < maxKey.length(); i++) {
   			char compare = maxKey.charAt(i);
   			if(compare == '1') {
   				updatedWord += input + " ";
   			} else {
   				updatedWord += word.getText().charAt(i*2) + " ";
   			}
   		}
   		
   		if(updatedWord.indexOf('_') < 0)
   			youWon();
   			
   		word.setText(updatedWord);
   		
   		return result;
   	}
   	
   	public ArrayList<String> filterWords(ArrayList<String> words, char input, Button pressed) {
   		ArrayList<String> yes, no, result;
   		yes = new ArrayList<String>();
   		no = new ArrayList<String>();
   		for(int i = 0; i < words.size(); i++) {
   			if(words.get(i).indexOf(input) >= 0){
   				yes.add(words.get(i));
   			} else {
   				no.add(words.get(i));
   			}
   		}
   		
   		if(yes.size() > no.size()) {
   			pressed.setTextColor(Color.GREEN);
   			pressed.setEnabled(false);
   			pressed.setBackground(this.getResources().getDrawable(R.drawable.roundedgray));
   			result = filterOnChar(yes, input);
   			return result;
   		}
   		
   		pressed.setTextColor(Color.RED);
   		pressed.setEnabled(false);
   		pressed.setBackground(this.getResources().getDrawable(R.drawable.roundedgray));
   		if(nrOfFaults++ < 9)
   			updateHangman(nrOfFaults);
   		else 
   			onBackPressed();
   			
		return no;
   	}
	
}
