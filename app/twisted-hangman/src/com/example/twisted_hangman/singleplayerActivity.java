package com.example.twisted_hangman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.twisted_hangman.sqlite.User;
import com.example.twisted_hangman.sqlite.Words_nl;
import com.example.twisted_hangman.sqlite.helper.DatabaseHelper;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class singleplayerActivity extends ActionBarActivity {
	ArrayList<String> words;
	DatabaseHelper db;
    
   	@Override
	public void onCreate(Bundle savedInstanceState) {
   		System.out.println("IN ONCREATE SINGLE");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_singleplayer);
		
		db = new DatabaseHelper(getApplicationContext());
        try {
			words = db.fillWords(getAssets().open("words.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   	
   	public ArrayList<String> filterWordsOnLengtj(ArrayList<String> words, int length) {
   		ArrayList<String> result = new ArrayList<String>();
   		
   		for(int i = 0; i < words.size(); i++) {
   			if(words.get(i).length() == length)
   				result.add(words.get(i));
   		}
   			
   		return result;
   	}
   	
   	public ArrayList<String> filterOnChar(ArrayList<String> words, char input) {
   		ArrayList<String> result = new ArrayList<String>();
   		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
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
   		
   		System.out.println(map.keySet());
   		return result;
   	}
   	
   	public ArrayList<String> filterWords(ArrayList<String> words, char input) {
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
   		System.out.println("yolo");
   		if(yes.size() > no.size()) {
   			result = filterOnChar(yes, input);
   			return result;
   		}
   		
		return no;
   	}
	
}
