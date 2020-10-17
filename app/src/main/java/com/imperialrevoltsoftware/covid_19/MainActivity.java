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
import org.json.JSONArray;


public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button submit = findViewById(R.id.submit);
        final SmsManager smgr = SmsManager.getDefault();
        final RequestQueue queue = Volley.newRequestQueue(this);


        String VAurl ="https://api.covidtracking.com/v1/states/va/current.json";
        String USurl = "https://api.covidtracking.com/v1/us/current.json";
        String Worldurl = "https://api.covid19api.com/summary";


        // Request a string response from the provided URL.
        StringRequest VAstringRequest = new StringRequest(Request.Method.GET, VAurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jsonobject = null;
                        try {
                            jsonobject = new JSONObject(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {


                            //State Information

                            int VAtotalPositiveCases = jsonobject.getInt("positive");
                            int VAtotalPositiveCasesIncrease = jsonobject.getInt("positiveIncrease");

                            //VA Total Positive Cases

                            TextView VAPositiveCases = findViewById(R.id.VACovidCasesNumber);
                            String totalPositiveCases = String.valueOf(VAtotalPositiveCases);
                            VAPositiveCases.setText("Total Positive Cases: " + totalPositiveCases);

                            //VA Total Positive Cases Increase

                            TextView VAPositiveCasesIncrease = findViewById(R.id.VACovidCasesIncrease);
                            String totalPositiveCasesIncrease = String.valueOf(VAtotalPositiveCasesIncrease);
                            VAPositiveCasesIncrease.setText("Total Positive Increase: " + totalPositiveCasesIncrease);


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
        }
        );
        queue.add(VAstringRequest);

        // Request a string response from the provided URL.
        StringRequest USstringRequest = new StringRequest(Request.Method.GET, USurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       JSONArray jsonarray = null;

                       try {

                             jsonarray = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        try {
                            int UStotalPositiveCases = 0;
                            int UStotalPositiveCasesIncrease = 0;

                            for(int i=0; i<jsonarray.length(); i++){

                                JSONObject obj = jsonarray.getJSONObject(i);
                                UStotalPositiveCases = obj.getInt("positive");
                                UStotalPositiveCasesIncrease = obj.getInt("positiveIncrease");

                                }

                            //US Information
                            //US Total Positive Cases

                            TextView USPositiveCases = findViewById(R.id.USpositiveCases);
                            USPositiveCases.setText("Total Positive Cases: " + UStotalPositiveCases);

                            //US Total Positive Cases Increase

                            TextView USPositiveCasesIncrease = findViewById(R.id.USpositiveCasesIncrease);
                            USPositiveCasesIncrease.setText("Total Positive Increase: " + UStotalPositiveCasesIncrease);


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
        }
        );

        queue.add(USstringRequest);

        // Request a string response from the provided URL.
        StringRequest WorldstringRequest = new StringRequest(Request.Method.GET, Worldurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jsonobject = null;

                        try {

                            jsonobject = new JSONObject(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        try {
                            int WorldtotalPositiveCases = 0;
                            int WorldtotalPositiveCasesIncrease = 0;

                            //for(int i=0; i<jsonarray.length(); i++){

                                //JSONObject obj = jsonarray.getJSONObject(i);
                                //WorldtotalPositiveCases = obj.getInt("positive");
                                //WorldtotalPositiveCasesIncrease = obj.getInt("positiveIncrease");

                            //}
                            WorldtotalPositiveCases = jsonobject.getJSONObject("Global").getInt("TotalConfirmed");
                            WorldtotalPositiveCasesIncrease = jsonobject.getJSONObject("Global").getInt("NewConfirmed");

                            //Log.d("totalconfirmed", String.valueOf(WorldtotalPositive));
                            //Log.d("newconfirmed", String.valueOf(WorldtotalPositiveCasesIncrease));


                            //US Information
                            //US Total Positive Cases

                            TextView WorldPositiveCases = findViewById(R.id.worldCovidCasesNumber);
                            String worldtotalPositiveCases = String.valueOf(WorldtotalPositiveCases);
                            WorldPositiveCases.setText("Total Positive Cases: " + WorldtotalPositiveCases);

                            //US Total Positive Cases Increase

                            TextView WorldPositiveCasesIncrease = findViewById(R.id.worldCovidCasesIncrease);
                            String worldtotalPositiveCasesIncrease = String.valueOf(WorldtotalPositiveCasesIncrease);
                            WorldPositiveCasesIncrease.setText("Total Positive Increase: " + WorldtotalPositiveCasesIncrease);


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
        }
        );

        queue.add(WorldstringRequest);



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



            }



        });


        }
        }