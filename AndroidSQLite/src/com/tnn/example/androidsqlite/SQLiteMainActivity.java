package com.tnn.example.androidsqlite;

import java.util.List;

import android.app.Activity;
import android.app.ApplicationErrorReport.AnrInfo;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SQLiteMainActivity extends Activity {

	DBHelper mHelper;
	List<String> friends;
	private ListView mListView;
	ArrayAdapter<String> mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite_main);
		Log.i("TagMIT", "onCreateMain");
		String[] lists = {""};
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lists);
		
		mHelper = new DBHelper(this);
		friends = mHelper.getFriendList();
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friends);
		
		mAdapter.setNotifyOnChange(true);
		mAdapter.notifyDataSetChanged();
//		setListAdapter(adapter);
//		ListView lv = (ListView) findViewById(android.R.id.list);
//		lv.setAdapter(adapter);
		mListView = (ListView) findViewById(R.id.listView1);
		mListView.setAdapter(mAdapter);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.i("TagMIT", "LVclick");
				setDetailActivity(position);
//				super.onListItemClick(parent, view, position, id);
			}
			
		});
	}
	
	protected void setDetailActivity(int position) {
		// TODO Auto-generated method stub
		Intent detail = new Intent(this, DetailActivity.class);
		String listName = friends.get(position);
		int index = listName.indexOf(" ");
		String columnId = listName.substring(0, index);
		
		detail.putExtra(Friend.Column.ID, columnId);
		startActivity(detail);
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	}



	@Override
	protected void onStart() {
		Log.v("TagMIT", "onStart");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		Log.v("TagMIT", "onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.v("TagMIT", "onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.v("TagMIT", "onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.v("TagMIT", "onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.v("TagMIT", "onDestroy");
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlite_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		Log.v("TagMIT", "::" + id);
		if (id == R.id.action_settings) {
			Log.v("TagMIT", "Setting");
			return true;
		}
		if (id == R.id.action_add) {
			Log.v("TagMIT", "Add");
			Intent addFriend = new Intent(this, AddFriendActivity.class);
			
			startActivity(addFriend);
			overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		}
		if (id == R.id.action_refresh) {
			Log.v("TagMIT", "Refresh");
//			mHelper = new DBHelper(this);
//			friends = mHelper.getFriendList();
//			updateData(friends);
			updateListviewDatabase();
		}
		return super.onOptionsItemSelected(item);
	}

	private void updateListviewDatabase() {
		mHelper = new DBHelper(this);
		friends = mHelper.getFriendList();
		mAdapter.clear();
		if(friends != null) {
			for(Object object : friends) {
				mAdapter.insert((String) object, mAdapter.getCount());
			}
		}
		mAdapter.notifyDataSetChanged();
	}

	public void updateData(List itemsArrayList) {
		mAdapter.clear();
		if(itemsArrayList != null) {
			for(Object object : itemsArrayList) {
				mAdapter.insert((String) object, mAdapter.getCount());
			}
		}
		mAdapter.notifyDataSetChanged();
	}

}
