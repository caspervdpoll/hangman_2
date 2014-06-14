package com.example.twisted_hangman;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.example.twisted_hangman.sqlite.User;
import com.example.twisted_hangman.sqlite.helper.DatabaseHelper;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class chooseuserActivity extends ListActivity {
	// URL VOOR INFO  http://www.mkyong.com/android/android-listview-example/
	// ALLE CODE STAAT BIJ HET EERSTE VOORBEELD, IK KAN ALLEEN DE setListAdapter NIET GOED KRIJGEN
	
	
	DatabaseHelper db;
	ArrayList<User> users;
	
	
	static final String usernames[] = {};
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		db = new DatabaseHelper(getApplicationContext(), "hangman", null, 2);
		
		users = db.getAllUsers();
		int counter = 0;
		for(User user: users) {
			usernames[counter] = user.getName();
			System.out.println(usernames[counter]);
			counter++;
		}
		
		
		setListAdapter(new ArrayAdapter<String>(this, R.id.container, usernames));
 
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
 
	}
 
}