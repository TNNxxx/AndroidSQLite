package com.tnn.example.androidsqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddFriendActivity extends Activity{
	private EditText mFirstName;
	private EditText mLastName;
	private EditText mTel;
	private EditText mEmail;
	private EditText mDescription;
	private Button mButtonOK;
	
	private DBHelper mHelper;
	private int ID = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		setContentView(R.layout.activity_addfriend);
		
		mFirstName = (EditText) findViewById(R.id.add_first_name);
		mLastName = (EditText) findViewById(R.id.add_last_name);
		mTel = (EditText) findViewById(R.id.add_tel);
		mEmail = (EditText) findViewById(R.id.add_email);
		mDescription = (EditText) findViewById(R.id.add_description);
		mButtonOK = (Button) findViewById(R.id.button_submit);
		
		mHelper = new DBHelper(this);
		
		mButtonOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(AddFriendActivity.this);
				builder.setTitle(getString(R.string.add_data_title));
				builder.setMessage(getString(R.string.add_data_message));
				
				builder.setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Log.v("TagMIT", "onClick Builder");
						Friend friend = new Friend();
						friend.setFirstName(mFirstName.getText().toString());
						friend.setLastName(mLastName.getText().toString());
						friend.setTel(mTel.getText().toString());
						friend.setEmail(mEmail.getText().toString());
						friend.setDescription(mDescription.getText().toString());
						Log.v("TagMIT", "onClick ID:" + ID);
						
						if (ID == -1) {
							mHelper.addFriend(friend);
							Log.v("TagMIT", "mHelper.addFriend");
						} else {
							friend.setId(ID);
							Log.v("TagMIT", "friend.setId");
							mHelper.updateFriend(friend);
						}
						finish();
					}
				});
				builder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						
					}
				});
				builder.show();
			}
		});
		
		Bundle bundle = getIntent().getExtras();
		if(bundle != null) {
			ID = bundle.getInt(Friend.Column.ID);
			String firstName = bundle.getString(Friend.Column.FIRST_NAME);
			String lastName = bundle.getString(Friend.Column.LAST_NAME);
			String tel = bundle.getString(Friend.Column.TEL);
			String email = bundle.getString(Friend.Column.EMAIL);
			String description = bundle.getString(Friend.Column.DESCRIPTION);
			
			mFirstName.setText(firstName);
			mLastName.setText(lastName);
			mTel.setText(tel);
			mEmail.setText(email);
			mDescription.setText(description);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		finish();
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		
		return super.onOptionsItemSelected(item);
	}
	
	
}
