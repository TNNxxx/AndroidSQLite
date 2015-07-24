package com.tnn.example.androidsqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

	private final String TAG = getClass().getSimpleName();
	
	private SQLiteDatabase sqliteDatabase;
	
	public DBHelper(Context context) {
		super(context, "devahoy_friends.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
//		String CREATE_FRIEND_TABLE_HC = "CREATE TABLE friend ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//					"first_name TEXT, last_name TEXT, tel TEXT, email TEXT, description TEXT)";
		String CREATE_FRIEND_TABLE = String.format("CREATE TABLE %s " +
					"(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)", 
					Friend.TABLE, Friend.Column.ID, Friend.Column.FIRST_NAME, Friend.Column.LAST_NAME,
					Friend.Column.TEL, Friend.Column.EMAIL, Friend.Column.DESCRIPTION);

		Log.i(TAG, CREATE_FRIEND_TABLE);
				
		db.execSQL(CREATE_FRIEND_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String DROP_FRIEND_TABLE_HC = "DROP TABLE IF EXIST friend";
		String DROP_FRIEND_TABLE = "DROP TABLE IF EXIST " + Friend.TABLE;
		
		db.execSQL(DROP_FRIEND_TABLE);
		
		Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);
		
		onCreate(db);
	}
	
	public void addFriend(Friend friend) {
		sqliteDatabase = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
//		values.put(Friend.Column.ID, friend.getId());
		values.put(Friend.Column.FIRST_NAME, friend.getFirstName());
		values.put(Friend.Column.LAST_NAME, friend.getLastName());
		values.put(Friend.Column.TEL, friend.getTel());
		values.put(Friend.Column.EMAIL, friend.getEmail());
		values.put(Friend.Column.DESCRIPTION, friend.getDescription());
		
		sqliteDatabase.insert(Friend.TABLE, null, values);
		
		sqliteDatabase.close();
	}
	
	public Friend getFriend(String id) {
		sqliteDatabase = this.getReadableDatabase();
		
		Cursor cursor = sqliteDatabase.query(Friend.TABLE, null, Friend.Column.ID + " = ? ", 
				new String[] { id }, null, null, null, null);
		
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		Friend friend = new Friend();
		
		friend.setId((int) cursor.getLong(0));
		friend.setFirstName(cursor.getString(1));
		friend.setLastName(cursor.getString(2));
		friend.setTel(cursor.getString(3));
		friend.setEmail(cursor.getString(4));
		friend.setDescription(cursor.getString(5));
		
		return friend;
	}
	
	public void updateFriend(Friend friend) {
		sqliteDatabase = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(Friend.Column.ID, friend.getId());
		values.put(Friend.Column.FIRST_NAME, friend.getFirstName());
		values.put(Friend.Column.LAST_NAME, friend.getLastName());
		values.put(Friend.Column.EMAIL, friend.getEmail());
		values.put(Friend.Column.DESCRIPTION, friend.getDescription());
		
		int row = sqliteDatabase.update(Friend.TABLE, values, 
				Friend.Column.ID + " = ?", 
				new String[] {String.valueOf(friend.getId()) });
		sqliteDatabase.close();
	}
	
	public void deleteFriend(String id) {
		sqliteDatabase = this.getWritableDatabase();
		
		//sqliteDatabase.delete(Friend.TABLE, Friend.Column.ID + " = ? ", new String[] { String.valueOf(friend.getId()) });
		sqliteDatabase.delete(Friend.TABLE, Friend.Column.ID + " = " + id, null);
		
		sqliteDatabase.close();
	}
	
	public List<String> getFriendList() {
		List<String> friends = new ArrayList<String>();
		sqliteDatabase = this.getWritableDatabase();
		Cursor cursor = sqliteDatabase.query(Friend.TABLE, null, null, null, null, null, null);
		
		if(cursor != null) {
			cursor.moveToFirst();
		}
		
		while (!cursor.isAfterLast()) {
			friends.add(cursor.getLong(0) + " " +
					cursor.getString(1) + " " + 
					cursor.getString(2));
			cursor.moveToNext();
		}
		
		sqliteDatabase.close();

		return friends;
	}
}
