package com.example.sitwith;

import java.text.SimpleDateFormat;
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


public class CheckListAdapter extends ArrayAdapter<Request> {  
    private LayoutInflater mInflater;   
    private List<Request> rqList;
    
    public CheckListAdapter(Context context, int textViewResourceId,List<Request> obj) {  
        super(context, textViewResourceId,obj);  
        
        this.rqList = obj;
        this.mInflater = LayoutInflater.from(context);  
    }  
      
    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
        if(convertView == null){    
  
            convertView = mInflater.inflate(R.layout.history_listview, null);    
        }    
            
        ViewHolder holder = null;    
        if(holder==null){    
            holder = new ViewHolder();    
            
            holder.historyRestaruantName = (TextView) convertView.findViewById(R.id.historyRestaruantName);    
            holder.historyStatus = (TextView) convertView.findViewById(R.id.historyStatus);    
            holder.historyTime = (TextView) convertView.findViewById(R.id.historyTime);        
            
            convertView.setTag(holder);    
        }  
        else{  
            holder = (ViewHolder)convertView.getTag();   
        }  
            
        
        Request rq = rqList.get(position);    
    
        //
        holder.historyRestaruantName.setText(rq.restaruantName);    
        holder.historyStatus.setText(rq.status);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        holder.historyTime.setText(format.format(rq.time));    
           
        //
        return convertView;  
    }  
      
    private static class ViewHolder  
    {  
        TextView historyRestaruantName;    
        TextView historyStatus;    
        TextView historyTime;    
    }  
      
      
}  
