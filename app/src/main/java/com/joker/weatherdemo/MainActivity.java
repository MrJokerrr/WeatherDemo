package com.joker.weatherdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private Gson mGson;
    private String authkey = "c7ba490a16c34a8c98a447cc069f7912";
    private String url = "http://api.36wu.com/Weather/GetWeather?district=%E5%8C%97%E4%BA%AC&authkey=" + authkey;

    private Button btnGetWeather;
    private TextView tvWeatherInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mGson = new Gson();
        btnGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestWeatherJson();
            }
        });

    }

    private void initView() {
        btnGetWeather = (Button) findViewById(R.id.btn_get_weather);
        tvWeatherInfo = (TextView) findViewById(R.id.tv_weather_info);
    }

    private void requestWeatherJson() {
        // 声明请求队列
        mRequestQueue = Volley.newRequestQueue(this);
        // 声明请求
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }        });
        mRequestQueue.add(request);
    }

    private void parseJson(JSONObject response) {
        // Weather weather = mGson.fromJson(response.toString(), Weather.class);
        tvWeatherInfo.setText(response.toString());
    }
}
