package com.example.twisted_hangman;

import java.io.IOException;
import java.util.ArrayList;

import com.example.twisted_hangman.sqlite.User;
import com.example.twisted_hangman.sqlite.helper.DatabaseHelper;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
 
public class chooseuserActivity extends ListActivity {
	User user;
	DatabaseHelper db;
	ArrayList<User> users;
	Button newuser;
	
	ArrayList<String> usernames = new ArrayList<String>();
	ArrayList<String> ids = new ArrayList<String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		final Context context = this;
	
		db = new DatabaseHelper(getApplicationContext(), "hangman", null, 2);
		
		//Create database
		try {
			db.createDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Get all users to make the listview
		users = db.getAllUsers();
		for(User user: users) {
			usernames.add(user.getName());
		}

		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_user, R.id.list_item, usernames));
		
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				User user = users.get((int)id);	

				Intent intent = new Intent(context, MainActivity.class);
				
				// Use Bundle to give argument to the activity, we send userID
				Bundle b = new Bundle();
			    b.putInt("id",user.getID());
			    
			    intent.putExtras(b);
                startActivity(intent);
			}
		});
		
		// Create footer, otherwise button for adding user is not shown
		View footerView =  ((LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_layout, null, false);
        listView.addFooterView(footerView);
        addListenerOnButtonNewUser();
    	}
 
	public void addListenerOnButtonNewUser() {
		final Context context = this;
		newuser = (Button) findViewById(R.id.new_user);
		newuser.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, newuserActivity.class);
                startActivity(intent);   
 
			}
		});
		
	}
}