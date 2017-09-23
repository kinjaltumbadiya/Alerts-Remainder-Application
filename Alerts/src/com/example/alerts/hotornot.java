package com.example.alerts;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class hotornot {
	
	public static final String KEY_IDANNI="idd";
	public static final String KEY_SENDER="sender";
	public static final String KEY_MSG="msg";
	public static final String KEY_RECEIVER="receiver";
	public static final String KEY_NEW="ok";
	public static final String KEY_DT="dt";
	

	public static final String KEY_ID="id";
	public static final String KEY_NAME="name";
	public static final String KEY_TEMP="temp";
	public static final String KEY_NUMBER="number";
	
	public static final String KEY_IDCUS="id";
	public static final String KEY_EVENT="event";
	public static final String KEY_DATE="date";

	
	
	public static final String KEY_REGID="regid";

	private static final String DATABASE_NAME="Hotornotdb122";

	private static final String DATABASE_ANNIVERSARY="message";
	private static final String DATABASE_BIRTHDAY="birthday";
	private static final String DATABASE_NEW="navubhai";
	private static final String DATABASE_PATH="PATH";
	private static final String DATABASER_PATHU="PATHU";
	
	
	private static final int DATABASE_VERSION=1;
	
	
	private dbhelper ourhelper;
	private final Context ourContext;
	private SQLiteDatabase ourdatabase;
	

	private static class dbhelper extends SQLiteOpenHelper{

		public dbhelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-genrated method stub
	
			
			db.execSQL("CREATE TABLE "+ DATABASE_ANNIVERSARY + " (" +  KEY_IDANNI + " INTEGER PRIMARY KEY AUTOINCREMENT ," +  KEY_SENDER + "," + KEY_MSG + "," + KEY_RECEIVER +"," + KEY_NEW +"," + DATABASER_PATHU +");"
					);
		
			db.execSQL("CREATE TABLE "+ DATABASE_BIRTHDAY + " (" +  KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +  KEY_NAME + "," + KEY_TEMP + "," + KEY_NUMBER +"," + DATABASE_PATH +");"
					);
			
			db.execSQL("CREATE TABLE "+ DATABASE_NEW + " (" +  KEY_IDCUS + " INTEGER PRIMARY KEY AUTOINCREMENT ," +  KEY_EVENT + "," + KEY_DATE + ");"
					);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS"+ DATABASE_NAME);
			onCreate(db);
			
			
		}
		
	}
	public hotornot(Context c) {
		ourContext=c;
		
	}
	public hotornot open()
	{
		ourhelper=new dbhelper(ourContext);
		ourdatabase=ourhelper.getWritableDatabase();
		
		return this;
	}
	public void close()
	{
		ourhelper.close();
	}
	
	
	public long insertanniversary(String name,String patnername,String datee,String mobile,String path) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(KEY_SENDER, name);
		cv.put(KEY_MSG, patnername);
		cv.put(KEY_RECEIVER, datee);
		cv.put(KEY_NEW, mobile);
		cv.put(DATABASER_PATHU, path);
		//cv.put(KEY_DT, dt);
		System.out.println("thai gayu bhai");
		return ourdatabase.insert(DATABASE_ANNIVERSARY, null, cv);
		
	}
	
	public Cursor getallmsg() {
		// TODO Auto-generated method stub
		String [] columes=new String[]{KEY_SENDER,KEY_MSG,KEY_RECEIVER,KEY_NEW,DATABASER_PATHU,KEY_IDANNI};
		Cursor c=ourdatabase.query(DATABASE_ANNIVERSARY, columes, null, null, null, null, null);
		String result="";
		int irow=c.getColumnIndex(KEY_SENDER);
		int iname=c.getColumnIndex(KEY_MSG);
		int ihot=c.getColumnIndex(KEY_NEW);
		int irec=c.getColumnIndex(KEY_RECEIVER);
		int ipath=c.getColumnIndex(DATABASER_PATHU);
		//int ihotness=c.getColumnIndex(KEY_DT);
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		{
			result=result + c.getString(irow) + "  " + c.getString(iname) +"  " + c.getString(ihot) + c.getString(ipath)+c.getString(irec)+"\n";
	
		}
		
		return c;
	}
	

	public Cursor getallcustommsg() {
		// TODO Auto-generated method stub
		String [] columes=new String[]{KEY_EVENT,KEY_DATE,KEY_IDCUS};
		Cursor c=ourdatabase.query(DATABASE_NEW, columes, null, null, null, null, null);
		String result="";
		int irow=c.getColumnIndex(KEY_EVENT);
		int iname=c.getColumnIndex(KEY_DATE);
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		{
			result=result + c.getString(irow) + "  " + c.getString(iname) +"  " + "\n";
	
		}
		System.out.print("jo aa custom na data==> "+result);
		return c;
	}
	
	
	public Cursor getallmsgok() {
		// TODO Auto-generated method stub
		String [] columes=new String[]{KEY_NAME,KEY_TEMP,KEY_NUMBER,DATABASE_PATH,KEY_ID};
		Cursor c=ourdatabase.query(DATABASE_BIRTHDAY, columes, null, null, null, null, null);
		String result="";
		//int iid=c.getColumnIndex(KEY_ID);
		int irow=c.getColumnIndex(KEY_NAME);
		int iname=c.getColumnIndex(KEY_TEMP);
		int ihotness=c.getColumnIndex(KEY_NUMBER);
		int ipath=c.getColumnIndex(DATABASE_PATH);
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		{
			result=result + c.getString(irow) + "  " + c.getString(iname) +"  " + c.getString(ihotness)+" "+c.getString(ipath) +"\n";
	
		}
		return c;
	}
	
	
	public long insertbirthday(String sender,String msg,String receiver,String path) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(KEY_NAME, sender);
		cv.put(KEY_TEMP, msg);
		cv.put(KEY_NUMBER, receiver);
		cv.put(DATABASE_PATH, path);
		System.out.println("data inserted");
		return ourdatabase.insert(DATABASE_BIRTHDAY, null, cv);
		
	}
	
	public long insertcustom(String event,String date) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(KEY_EVENT, event);
		cv.put(KEY_DATE, date);
		
		System.out.println("data inserted bhai jaan in custom");
		
		return ourdatabase.insert(DATABASE_NEW, null, cv);
		
	}
	// Deleting single contact
    public void Delete_Contact(int id) {

	
	ourdatabase.delete(DATABASE_BIRTHDAY, KEY_ID + " = ?",
		new String[] { String.valueOf(id) });
	ourdatabase.close();
    }
    
    
 // Deleting single contact
    public void Delete_Contactanni(int id) {

	
	ourdatabase.delete(DATABASE_ANNIVERSARY,  KEY_IDANNI + " = ?",
		new String[] { String.valueOf(id) });
	ourdatabase.close();
    }
    
 // Deleting single contact
    public void Delete_Contactcus(int id) {

	
	ourdatabase.delete(DATABASE_NEW,  KEY_IDCUS + " = ?",
		new String[] { String.valueOf(id) });
	ourdatabase.close();
    }
    //update Record in db
  public  Cursor Get_birthday_info(int id) {
    	
    	Cursor cursor = ourdatabase.query(DATABASE_BIRTHDAY, new String[] { KEY_NAME,KEY_TEMP,KEY_NUMBER }, KEY_ID + "=?",
    		new String[] { String.valueOf(id) }, null, null, null, null);
    	if (cursor != null)
    	    cursor.moveToFirst();

    	

    	return cursor;
    	
    	
        }
  
  public long updatebirthday(String sender,String msg,String receiver,int KEY_ID) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(KEY_NAME, sender);
		cv.put(KEY_TEMP, msg);
		cv.put(KEY_NUMBER, receiver);
		
		System.out.println("data inserted");
		return ourdatabase.update(DATABASE_BIRTHDAY, cv, KEY_ID + " = ?",new String[] { String.valueOf(KEY_ID) });
				
		
	}
	
	}
