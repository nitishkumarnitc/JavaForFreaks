package com.example.nitish.javaforfreaks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TopicsActivity extends AppCompatActivity {

    public ArrayList<String> topics=new ArrayList<>();
    public String[] drawerContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        GridView gridView=(GridView)findViewById(R.id.gridview);
        setTopics();
        gridView.setAdapter(new TopicsAdapter(this,topics));
    }

    private void setTopics(){
        topics.add("inheritance");
        topics.add("polymorphism");
        topics.add("encapsulation");
        topics.add("abstraction");
        topics.add("interface");
        topics.add("constructor");
    }

}
