package com.imperialrevoltsoftware.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button submit = findViewById(R.id.submit);
        final SmsManager smgr = SmsManager.getDefault();




        final RequestQueue queue = Volley.newRequestQueue(this);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                smgr.sendTextMessage("7036775881", null, "Covid Questions Done", null, null);

                final CheckBox checkBox0 = findViewById(R.id.checkBox0);
                final CheckBox checkBox1 = findViewById(R.id.checkBox1);
                final CheckBox checkBox2 = findViewById(R.id.checkBox2);
                final CheckBox checkBox3 = findViewById(R.id.checkBox3);
                final CheckBox checkBox4 = findViewById(R.id.checkBox4);
                final CheckBox checkBox5 = findViewById(R.id.checkBox5);
                final CheckBox checkBox6 = findViewById(R.id.checkBox6);
                final CheckBox checkBox7 = findViewById(R.id.checkBox7);
                final CheckBox checkBox8 = findViewById(R.id.checkBox8);

                checkBox0.setChecked(false);
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);

                String url ="https://api.covidtracking.com/v1/states/va/current.json";

                    Log.d("URL Variable",url);

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                JSONObject jsonobject = null;
                                try {
                                    jsonobject = new JSONObject(response);
                                    Log.d("respon", response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    int totalpositiveCases = jsonobject.getInt("positive");

                                    TextView USDeaths = findViewById(R.id.USCovidCasesNumber);
                                    //USDeaths.setText("Total Positive Cases" + totalpositiveCases);
                                    String totalPositiveCases = String.valueOf(totalpositiveCases);
                                    USDeaths.setText("Total Positive Cases: " + totalpositiveCases);
                                    Log.d("Deaths Variable", String.valueOf(totalpositiveCases));
                                    //System.out.println(deaths);
                                    //USDeaths.setText("This");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                                // Display the first 500 characters of the response string.
                                //textView.setText("Response is: "+ response.substring(0,500));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //textView.setText("That didn't work!");
                    }
                });

queue.add(stringRequest);

            }



        });


        }}