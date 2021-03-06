package com.example.study_path;

import java.io.IOException; 

import android.content.ContentValues;
import android.content.Context; 
import android.database.Cursor; 
import android.database.SQLException; 
import android.database.sqlite.SQLiteDatabase; 
import android.util.Log; 
 
public class DataBaseTestAdapter1  
{ 
    protected static final String TAG = "DataAdapter"; 
 
    private final Context mContext; 
    private SQLiteDatabase mDb; 
    private DataBaseHelper1 mDbHelper; 
 
    public DataBaseTestAdapter1(Context context)  
    { 
        this.mContext = context; 
        mDbHelper = new DataBaseHelper1(mContext); 
    } 
 
    public DataBaseTestAdapter1 createDatabase() throws SQLException  
    { 
        try  
        { 
            mDbHelper.createDataBase(); 
        }  
        catch (IOException mIOException)  
        { 
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase"); 
            throw new Error("UnableToCreateDatabase"); 
        } 
        return this; 
    } 
 
    public DataBaseTestAdapter1 open() throws SQLException  
    { 
        try  
        { 
            mDbHelper.openDataBase(); 
            mDbHelper.close(); 
            mDb = mDbHelper.getReadableDatabase(); 
        }  
        catch (SQLException mSQLException)  
        { 
            Log.e(TAG, "open >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
        return this; 
    } 
 
    public void close()  
    { 
        mDbHelper.close(); 
    } 
 
     public Cursor getTestData(String sql) 
     { 
         try 
         { 
 
             Cursor mCur = mDb.rawQuery(sql, null); 
             if (mCur!=null) 
             { 
                mCur.moveToNext(); 
             } 
             return mCur; 
         } 
         catch (SQLException mSQLException)  
         { 
             Log.e(TAG, "getTestData >>"+ mSQLException.toString()); 
             throw mSQLException; 
         } 
     }
     
     

 	public boolean SaveData(String Code, String Type) 
 	{
 		try
 		{
 			ContentValues cv = new ContentValues();
 			cv.put("Code", Code);
 			//cv.put("Number", email);
 			
 			mDb.insert(Type, null, cv);
 			
 			Log.d("Saved", "informationsaved");
 			return true;
 			
 		}
 		catch(Exception ex)
 		{
 			Log.d("Saved", ex.toString());
 			return false;
 		}
 	}
     
    public void del(String name)  
    { 
    	mDb.delete(name, null, null);

    } 
    
    public void update(String Value, String Type)  
    { 
    	mDb.execSQL("UPDATE Preference SET Value='" + Value +"' WHERE Type='" + Type +"'");

    } 

} 

