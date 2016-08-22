package com.example.nitish.javaforfreaks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nitish.javaforfreaks.app.AppController;
import com.example.nitish.javaforfreaks.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SubjectiveQuestions extends AppCompatActivity {

    private int topicId;
    private String TAG="SubjectiveQuestions";
    private JSONArray questionsAnswers;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> questions=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjective);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view) ;

        topicId=getIntent().getIntExtra(Constants.TOPIC_ID,0);
        getQuestionsFromServer(topicId);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    private void getQuestionsFromServer(int topicId){

        JSONObject params=new JSONObject();
        try {
            params.put(Constants.TAG,"get_subjective_questions");
            params.put(Constants.TOPIC_ID,topicId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, Constants.HOME_URL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(Constants.SERVER_RESPONSE,response.toString());
                        displayQuestions(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NetworkError)
                    Toast.makeText(SubjectiveQuestions.this, Constants.NO_NETWORK_CONNECTION, Toast.LENGTH_SHORT).show();
            }
        });

        AppController.getInstance().addToRequestQueue(request,TAG);

    }

    private void displayQuestions(JSONObject response){
        try {
            questionsAnswers=response.getJSONArray(Constants.SERVER_RESPONSE);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i=0;i<questionsAnswers.length();i++){
            try {
                questions.add(questionsAnswers.getJSONObject(i).getString(Constants.QUESTION));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
