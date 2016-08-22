package com.example.nitish.javaforfreaks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nitish.javaforfreaks.util.Constants;

import java.util.ArrayList;

/**
 * Created by nitish on 17-08-2016.
 */
public class TopicsAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> topics;

    // Constructor
    public TopicsAdapter(Context c, ArrayList<String> topics) {
        mContext = c;
        this.topics=topics;
    }

    @Override
    public int getCount() {
        return topics.size();
    }

    @Override
    public String getItem(int i) {
        return topics.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            LayoutInflater mInflater=(LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view=mInflater.inflate(R.layout.topic_item,null);
        }

        TextView topicFirstName=(TextView) view.findViewById(R.id.topic_first_name);
        TextView topicFullName=(TextView) view.findViewById(R.id.topic_full_name);

        topicFirstName.setText(Character.toUpperCase(getItem(i).charAt(0))+"");
        topicFullName.setText(Character.toUpperCase(getItem(i).charAt(0))+getItem(i).substring(1));





        return view;
    }


}
