package com.example.sitwith;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class AvailTableListAdapter extends ArrayAdapter<Table>{
	private LayoutInflater mInflater;   
    private ArrayList<Table> tableList;
    
    public AvailTableListAdapter(Context context, int textViewResourceId,ArrayList<Table> obj) {  
        super(context, textViewResourceId,obj);  
        
        this.tableList = obj;
        this.mInflater = LayoutInflater.from(context);
        System.out.println(tableList.size());
    }  
      
    
    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
        if(convertView == null){    
  
            convertView = mInflater.inflate(R.layout.available_table_listview, null);    
        }    
            
        ViewHolder holder = null;    
        if(holder==null){    
            holder = new ViewHolder();    
            
            holder.availableTime = (TextView) convertView.findViewById(R.id.available_table_time);        
            
            convertView.setTag(holder);    
        }  
        else{  
            holder = (ViewHolder)convertView.getTag();   
        }  
            
        
        Table table = tableList.get(position);    
        System.out.println(table.time);
        //System.out.println(res.neiborhood);
        holder.availableTime.setText(table.time.toString());    
           
        //System.out.println(holder.resLocation);
        return convertView;  
    }  
      
    private static class ViewHolder  
    {  
        TextView availableTime;   
    }  
      
      
}

