package com.imperialrevoltsoftware.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

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

        String httpsURL = "https://api.covidtracking.com/v1/states/va/current.json";
        URL myurl = null;
        try {
            myurl = new URL(httpsURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpsURLConnection con = null;
        try {
            con = (HttpsURLConnection)myurl.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream ins = null;
        try {
            ins = con.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader in = new BufferedReader(isr);


        String data = null;
        try {
            data = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(data);

        JSONObject jsonobject = null;
        try {
            jsonobject = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int deaths = 0;
        try {
            deaths = jsonobject.getInt("death");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(deaths);







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


        }}