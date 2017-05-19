package com.example.mwi.weatherapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.mwi.weatherapp.Adapter.AdapterCuaca;
import com.example.mwi.weatherapp.Model.Cuaca;
import com.yalantis.phoenix.PullToRefreshView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/*
Dibuat oleh : Muhammad Wildan Iskandar
Created at : May, 19 2017
*/
public class MainActivity extends AppCompatActivity {
    private final static String LINK_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=Bandung&APPID=2939b4f9a70e7dd25e181b06ab14bc5d&mode=json&units=metric&cnt=5";
    private ArrayList<Cuaca> daftarCuaca = new ArrayList<>();
    private AdapterCuaca adapter;
    private PullToRefreshView mPullToRefreshView;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mListView = (ListView) findViewById(R.id.listCuaca);
        getAllWeather();
        adapter = new AdapterCuaca(getApplicationContext(), daftarCuaca);
        mListView.setAdapter(adapter);
        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        daftarCuaca = new ArrayList<Cuaca>();
                        getAllWeather();
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 2000);
            }
        });


    }


    private void getAllWeather() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, LINK_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject data = new JSONObject(response);
                            JSONArray list = data.getJSONArray("list");
                            for (int i = 0; i < list.length(); i++) {
                                JSONObject object = list.getJSONObject(i);
                                String tgl = object.getString("dt");
                                JSONObject objectTemp = object.getJSONObject("temp");
                                int derajat = objectTemp.getInt("day");
                                JSONArray arrayStatus = object.getJSONArray("weather");
                                JSONObject objectStatus = arrayStatus.getJSONObject(0);
                                String status = objectStatus.getString("main");
                                daftarCuaca.add(new Cuaca(tgl, status, derajat));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("error", e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), "erroring: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("erroring", error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                try {
                    //Adding parameters to request

                    //returning parameter
                    return params;
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("error", e.getMessage());
                    return params;
                }
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
