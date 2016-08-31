package com.example.laxmi;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Sas extends Activity implements OnItemClickListener
{
	SQLiteDatabase db;
	Cursor c;
	ContentValues cv;
	String name=null,gen=null,edu=null,skill=null,rat=null;
	ListView lv;
	ArrayList<HashMap<String, String>> list=null;
	AlertDialog.Builder alert;
	
	private static String tag_id="ID";
	private static String tag_name="NAME";
	private static String tag_gen="GENDER";
	private static String tag_e="EDU";
	private static String tag_s="SKILL";
	private static String tag_r="RAT";
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sas);
		lv=(ListView)findViewById(R.id.listView1);
		list=new ArrayList<HashMap<String,String>>();
		
		alert=new AlertDialog.Builder(Sas.this);
		
		db=openOrCreateDatabase("shiv", SQLiteDatabase.CREATE_IF_NECESSARY, null);
		c=db.rawQuery("select * from DEV", null);
		c.moveToFirst();
		do
		{
			int id=c.getInt(c.getColumnIndex("ID"));
			name=c.getString(c.getColumnIndex("NAME"));
			gen=c.getString(c.getColumnIndex("GENDER"));
			edu=c.getString(c.getColumnIndex("EDU"));
			skill=c.getString(c.getColumnIndex("SKILL"));
			rat=c.getString(c.getColumnIndex("RAT"));
			
			HashMap<String, String> map=new HashMap<String, String>();
			
			map.put(tag_id, id+"");
			map.put(tag_name, name);			
			map.put(tag_gen,gen);			
			map.put(tag_e, edu);			
			map.put(tag_s, skill);	
			map.put(tag_r, rat);
			
			list.add(map);
		}while(c.moveToNext());
		c.close();
		ListAdapter adapter=new SimpleAdapter(Sas.this,list,R.layout.raw_sas,new String[] {tag_id,tag_name,tag_gen,tag_e,tag_s,tag_r},new int[] {R.id.mno,R.id.mname,R.id.mgen,R.id.medu,R.id.mskill,R.id.mrat});
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
	{
		// TODO Auto-generated method stub
		alert.setTitle("HEELPPPP");
		alert.setMessage("R u sure?");
		
		String string=((TextView)arg1.findViewById(R.id.mno)).getText().toString();
		final int id=Integer.parseInt(string);
		
		alert.setPositiveButton("DELETE", new DialogInterface.OnClickListener() 
		{
			
			@Override
			public void onClick(DialogInterface arg0, int arg1)
			{
				// TODO Auto-generated method stub
				cv=new ContentValues();
				
				cv.put("ID", id+"");
				
				db.delete("DEV", "id="+id, null);
				
				Intent i=new Intent(Sas.this,Sas.class);
				startActivity(i);
				
			}
		});
		
		alert.setNegativeButton("UPDATE", new DialogInterface.OnClickListener() 
		{
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) 
			{
				// TODO Auto-generated method stub

				Intent i=new Intent(Sas.this,Ras.class);
				i.putExtra("key", id+"");
				startActivity(i);
				
			}
		});
		alert.show();
		Toast.makeText(this, id+"", Toast.LENGTH_LONG).show();
		
	}
}
