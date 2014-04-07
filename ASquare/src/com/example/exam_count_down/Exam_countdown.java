package com.example.exam_count_down;

import java.util.ArrayList;
import java.util.List;

import com.example.asquare.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;



public class Exam_countdown extends Activity {



	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.exam_main);
		

		
		
	    // TODO Auto-generated method stub
		 button1 = (Button)findViewById(R.id.Add);
		 button2 = (Button)findViewById(R.id.Delete);
		 button3 = (Button)findViewById(R.id.reset);
      	 listView1 = (ListView)findViewById(R.id.listView1);
      	 Code = (EditText)findViewById(R.id.Code);
      	 Date = (EditText)findViewById(R.id.Date);
      	 
      	ArrayAdapter<String> adapter;
      	adapter = new ArrayAdapter<String>(Exam_countdown.this, android.R.layout.simple_list_item_1);   
      	List<String> data = new ArrayList<String>();
      	data = get_list();									//get from database
      	for (String i : data)
      		adapter.add(i);
      	
  		listView1.setAdapter(adapter);


		//notification bar
        int j = 1;
        for (String i : data){
			Intent intent = new Intent(this,MyBroadcastReceiver.class);
	        intent.putExtra ("Course", i);
	        intent.putExtra ("Num", j);

	
			PendingIntent pendingIntent = PendingIntent.getBroadcast(
					this.getApplicationContext(), 234324243+j, intent, 0);
			AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
			alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ (1 * 1000), pendingIntent);
			j++;
        }
		 
		 button1.setOnClickListener(new Button.OnClickListener(){ 
 	        @Override
 	        public void onClick(View v) {
 	            // TODO Auto-generated method stub
 	        	add_item(Code.getText().toString(), Date.getText().toString());				//add to database

 	        	ArrayAdapter<String> adapter;
 	        	adapter = new ArrayAdapter<String>(Exam_countdown.this, android.R.layout.simple_list_item_1);   
 	        	List<String> data = new ArrayList<String>();
 	        	data = get_list();									//get from database
 	        	for (String i : data)
 	        		adapter.add(i);
 	        	
<<<<<<< HEAD
 	    		listView1.setAdapter(adapter);
=======
 	        	
 	    		
 	    //		listView1.setAdapter(adapter);
>>>>>>> refs/remotes/origin/master
 	    		}
 	    });
		 
		 button2.setOnClickListener(new Button.OnClickListener(){ 
	 	        @Override
	 	        public void onClick(View v) {
	 	            // TODO Auto-generated method stub
	 	        	delete_item(Code.getText().toString()); 
	 	        	
	 	        	ArrayAdapter<String> adapter;
	 	        	adapter = new ArrayAdapter<String>(Exam_countdown.this, android.R.layout.simple_list_item_1);   
	 	        	List<String> data = new ArrayList<String>();
	 	        	data = get_list();									//get from database
	 	        	for (String i : data)
	 	        		adapter.add(i);
	 	        	
	 	    		listView1.setAdapter(adapter);
	 	    		}
	 	    });
		 
		 button3.setOnClickListener(new Button.OnClickListener(){ 
	 	        @Override
	 	        public void onClick(View v) {
	 	            // TODO Auto-generated method stub
	 	        	delete_table();
	 	        	
	 	        	ArrayAdapter<String> adapter;
	 	        	adapter = new ArrayAdapter<String>(Exam_countdown.this, android.R.layout.simple_list_item_1);   
	 	        	List<String> data = new ArrayList<String>();
	 	        	data = get_list();									//get from database
	 	        	for (String i : data)
	 	        		adapter.add(i);
	 	        	
	 	    		listView1.setAdapter(adapter);
	 	    		}
	 	    });
	}

	
	public void add_item(String Code, String Date) {
	    // TODO Auto-generated method stub
		DataBaseTestAdapter mDbHelper = new DataBaseTestAdapter(this);         
    	mDbHelper.createDatabase();       
    	mDbHelper.open(); 
    	
    	mDbHelper.SaveData(Code, Date);
    	mDbHelper.close();
	}
	
	public void delete_item(String Code) {
	    // TODO Auto-generated method stub
		DataBaseTestAdapter mDbHelper = new DataBaseTestAdapter(this);         
    	mDbHelper.createDatabase();       
    	mDbHelper.open(); 
    	
    	mDbHelper.delete_one(Code);
    	mDbHelper.close();
	}
	
	public void delete_table() {
	    // TODO Auto-generated method stub
		DataBaseTestAdapter mDbHelper = new DataBaseTestAdapter(this);         
    	mDbHelper.createDatabase();       
    	mDbHelper.open(); 
    	
    	mDbHelper.delele_table();
    	mDbHelper.close();
	}
	
	public List<String> get_list(){
		DataBaseTestAdapter mDbHelper = new DataBaseTestAdapter(this);         
    	mDbHelper.createDatabase();       
    	mDbHelper.open(); 
    	
		String sql ="SELECT Code, Date FROM Exam ORDER BY Date"; 
		Cursor testdata = mDbHelper.getTestData(sql); 
		String Code = DataBaseUtility.GetColumnValue(testdata, "Code");
		String Date = DataBaseUtility.GetColumnValue(testdata, "Date");
		
    	List<String> list;
     	list = new ArrayList<String>();
    	list.add(Code + "\t\t\t\t\t" + Date);
    	while (testdata.moveToNext()){
    		 
    		Code = DataBaseUtility.GetColumnValue(testdata, "Code");
    		Date = DataBaseUtility.GetColumnValue(testdata, "Date");
        	list.add(Code + "\t\t\t\t\t" + Date);
    	}
		
    	return list;		
	}
	    		
	private Button button1;
	private Button button2;
	private Button button3;
	private ListView listView1;
	private EditText Code;
	private EditText Date;
}
