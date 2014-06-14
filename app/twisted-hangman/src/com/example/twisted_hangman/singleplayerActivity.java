package com.example.twisted_hangman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.twisted_hangman.sqlite.helper.DatabaseHelper;

import android.graphics.Color;
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
	
    
   	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_singleplayer);
		
		db = new DatabaseHelper(getApplicationContext(), null, null, nrOfFaults);
        
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
        
        words = filterWordsOnLength(words, 5);
        for(int i = 0; i < words.size(); i++)
        	System.out.println(words.get(i).toString());
        
        String wordToPrint = ""; 
        for(int i = 0; i < 5; i++)
        	wordToPrint += "_ ";
        
        word.setText(wordToPrint);
        nrOfFaults = 0;
	}
   	
   	public void onClick(View v) {
   		switch(v.getId()) {
		    case R.id.A: words = filterWords(words, 'A', A);break;
		    case R.id.B: words = filterWords(words, 'B', B);break;
		    case R.id.C: words = filterWords(words, 'C', C);break;
		    case R.id.D: words = filterWords(words, 'D', D);break;
		    case R.id.E: words = filterWords(words, 'E', E);break;
		    case R.id.F: words = filterWords(words, 'F', F);break;
		    case R.id.G: words = filterWords(words, 'G', G);break;
		    case R.id.H: words = filterWords(words, 'H', H);break;
		    case R.id.I: words = filterWords(words, 'I', I);break;
		    case R.id.J: words = filterWords(words, 'J', J);break;
		    case R.id.K: words = filterWords(words, 'K', K);break;
		    case R.id.L: words = filterWords(words, 'L', L);break;
		    case R.id.M: words = filterWords(words, 'M', M);break;
		    case R.id.N: words = filterWords(words, 'N', N);break;
		    case R.id.O: words = filterWords(words, 'O', O);break;
		    case R.id.P: words = filterWords(words, 'P', P);break;
		    case R.id.Q: words = filterWords(words, 'Q', Q);break;
		    case R.id.R: words = filterWords(words, 'R', R_button);break;
		    case R.id.S: words = filterWords(words, 'S', S);break;
		    case R.id.T: words = filterWords(words, 'T', T);break;
		    case R.id.U: words = filterWords(words, 'U', U);break;
		    case R.id.V: words = filterWords(words, 'V', V);break;
		    case R.id.W: words = filterWords(words, 'W', W);break;
		    case R.id.X: words = filterWords(words, 'X', X);break;
		    case R.id.Y: words = filterWords(words, 'Y', Y);break;
		    case R.id.Z: words = filterWords(words, 'Z', Z);break;
   		}
   		System.out.println("Words " + words);
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
   		
   		//System.out.println(map);
   		
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
   			pressed.setBackgroundColor(Color.GRAY);
   			result = filterOnChar(yes, input);
   			return result;
   		}
   		
   		pressed.setTextColor(Color.RED);
   		pressed.setEnabled(false);
   		pressed.setBackgroundColor(Color.GRAY);
   		if(nrOfFaults++ < 9)
   			updateHangman(nrOfFaults);
   		else 
   			onBackPressed();
   			
		return no;
   	}
	
}
