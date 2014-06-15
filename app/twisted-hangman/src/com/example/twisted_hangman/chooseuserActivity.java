package com.example.twisted_hangman;

import java.lang.reflect.Array;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class chooseuserActivity extends ListActivity {
	// URL VOOR INFO  http://www.mkyong.com/android/android-listview-example/
	// ALLE CODE STAAT BIJ HET EERSTE VOORBEELD, IK KAN ALLEEN DE setListAdapter NIET GOED KRIJGEN
	
	User user;
	DatabaseHelper db;
	ArrayList<User> users;
	Button newuser;
	
	ArrayList<String> usernames = new ArrayList<String>();
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		final Context context = this;
	
		db = new DatabaseHelper(getApplicationContext(), "hangman", null, 2);
		
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
			    // When clicked, show a toast with the TextView text
				System.out.println(id);
				user = db.getUserById((int)id);
				Intent intent = new Intent(context, singleplayerActivity.class);
				System.out.println("yolo");
				System.out.println(user.getName());
				System.out.println(user.getGameType());
				System.out.println(user.getWordLength());
				
				Bundle b = new Bundle();
			    b.putString("name", user.getName());
			    b.putInt("amount_of_letters", user.getWordLength());
			    b.putString("type", user.getGameType());
			    b.putInt("id",(int)id);
			    
				System.out.println(b.get("name"));
				System.out.println(b.get("id"));
				System.out.println(b.get("amount_of_letters"));
			    
			    intent.putExtras(b);
                startActivity(intent);
			}
		});
		
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