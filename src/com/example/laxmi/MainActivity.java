package com.example.laxmi;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity  implements OnClickListener,OnItemSelectedListener,OnRatingBarChangeListener
{
	EditText ed;
	Button btn;
	RadioGroup rg;
	RatingBar r;
	Spinner sp;
	ArrayAdapter<CharSequence>adapter;
	CheckBox c1,c2;
	
	String rating=null,rat=null,c=null,g=null,s=null,name=null;
	ContentValues cv;
	
	String str="CREATE TABLE IF NOT EXISTS DEV(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,GENDER TEXT,EDU TEXT,SKILL TEXT,RAT TEXT)";
	
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed=(EditText)findViewById(R.id.edname);
		
		r=(RatingBar)findViewById(R.id.ratingBar1);
		r.setOnRatingBarChangeListener(this);
		
		btn=(Button)findViewById(R.id.button1);
		btn.setOnClickListener(this);
		
		rg=(RadioGroup)findViewById(R.id.radioGroup1);
		
		c1=(CheckBox)findViewById(R.id.chandy);
		c2=(CheckBox)findViewById(R.id.chp);
		
		sp=(Spinner)findViewById(R.id.spinner1);
		adapter=ArrayAdapter.createFromResource(MainActivity.this, R.array.Sp, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(this);
		
		db=openOrCreateDatabase("shiv", SQLiteDatabase.CREATE_IF_NECESSARY, null);
		db.execSQL(str);
				
	}
	@Override
	public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) 
	{
		// TODO Auto-generated method stub
		Float f=arg1;
		rating=f.toString();
		Toast.makeText(this, rating, Toast.LENGTH_LONG).show();
		
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) 
	{
		// TODO Auto-generated method stub
		TextView tv=(TextView)arg1;
		String st=tv.getText().toString();
		if(st.equalsIgnoreCase("BE"))
		{
			s="BE";
		}
		if(st.equalsIgnoreCase("BCA"))
		{
			s="BCA";
		}
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View arg0)
	{
		// TODO Auto-generated method stub
		int id=rg.getCheckedRadioButtonId();
		RadioButton rb=(RadioButton)findViewById(id);
		String string=rb.getText().toString();
		if(string.equalsIgnoreCase("MALE"))
		{
			g="MALE";
		}
		if(string.equalsIgnoreCase("FEMALE"))
		{
			g="FEMALE";
		}
		
		if(c1.isChecked())
		{
			c="Android";
		}
		if(c2.isChecked())
		{
			c="Php";
		}
		
		if(btn==arg0)
		{
			name=ed.getText().toString();
			rat=rating;
			
			cv=new ContentValues();
			cv.put("NAME", name);
			cv.put("EDU", s);
			cv.put("GENDER", g);
			cv.put("SKILL", c);
			cv.put("RAT", rat);
			
			db.insert("DEV", null, cv);
			
			Toast.makeText(this, cv+"", Toast.LENGTH_LONG).show();
			
			Intent i=new Intent(MainActivity.this,Sas.class);
			startActivity(i);
			
		}
		
	}

	

}
