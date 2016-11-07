package sp.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SmsHelper extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "smslist.db";
	private static final int SCHEMA_VERSION =2;
	
	public SmsHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE sms_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, time TEXT, sender TEXT, message TEXT);");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
	
	public Cursor getAll() {
		return (getReadableDatabase().rawQuery("SELECT _id, time, sender, message FROM sms_table", null));
	}
	
	public Cursor getById(String id){
		String[] args = { id };
		
		return (getReadableDatabase().rawQuery("SELECT _id, time, sender, message FROM sms_table WHERE _ID=?", args));
	}
	
	public void insert(String time, String sender, String message) {
		
		ContentValues cv = new ContentValues();
		
		cv.put("time", time);
		cv.put("sender", sender);
		cv.put("message", message);
		
		getWritableDatabase().insert("sms_table", "sender", cv);
	}
	
	public String getID(Cursor c){
		return (c.getString(0));
	}
	
	public String getTime(Cursor c){
		return (c.getString(1));
	}
	
	public String getSender(Cursor c){
		return (c.getString(2));
	}
	
	public String getMessage(Cursor c){
		return (c.getString(3));
	}
	
}
