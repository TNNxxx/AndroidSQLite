package com.tnn.example.androidsqlite;

import android.provider.BaseColumns;

public class Friend {
	private int id;
	private String firstName;
	private String lastName;
	private String tel;
	private String email;
	private String description;

	public static final String DATABASE_NAME = "devahoy_friends.db";
	public static final int DATABASE_VERSION = 1;
	public static final String TABLE = "friend";
	
	public class Column {
		public static final String ID = BaseColumns._ID;
		public static final String FIRST_NAME = "first_name";
		public static final String LAST_NAME = "last_name";
		public static final String TEL = "tel";
		public static final String EMAIL = "email";
		public static final String DESCRIPTION = "description";
	}
	
	// Default Constructor
	public Friend() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Constructor
	public Friend(int id, String firstName, String lastName, String tel,
			String email, String description) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.tel = tel;
		this.email = email;
		this.description = description;
	}
	
	// Getter, Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
