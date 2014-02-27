package com.example.sitwith;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ListAdapter extends ArrayAdapter<Restaurant> {  
    private LayoutInflater mInflater;   
    private ArrayList<Restaurant> resList;
    
    public ListAdapter(Context context, int textViewResourceId,ArrayList<Restaurant> obj) {  
        super(context, textViewResourceId,obj);  
        
        this.resList = obj;
        this.mInflater = LayoutInflater.from(context);  
    }  
      
    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
        if(convertView == null){    
  
            convertView = mInflater.inflate(R.layout.listview, null);    
        }    
            
        ViewHolder holder = null;    
        if(holder==null){    
            holder = new ViewHolder();    
            
            holder.resName = (TextView) convertView.findViewById(R.id.resName);    
            holder.resNeiborhood = (TextView) convertView.findViewById(R.id.resNeiborhood);    
            holder.resLocation = (TextView) convertView.findViewById(R.id.resLocation);        
            
            convertView.setTag(holder);    
        }  
        else{  
            holder = (ViewHolder)convertView.getTag();   
        }  
            
        
        Restaurant res = resList.get(position);    
    
        //System.out.println(res.neiborhood);
        holder.resName.setText(res.name);    
        holder.resNeiborhood.setText(res.neiborhood);    
        holder.resLocation.setText(res.address);    
           
        //System.out.println(holder.resLocation);
        return convertView;  
    }  
      
    private static class ViewHolder  
    {  
        TextView resName;    
        TextView resNeiborhood;    
        TextView resLocation;    
    }  
      
      
}  
