package com.example.app;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_today:
                    transaction.replace(R.id.content, new TodayFragment()).commit();
                    return true;
                case R.id.navigation_log:
                    transaction.replace(R.id.content, new LogFragment()).commit();
                    return true;
                case R.id.navigation_map:
                    transaction.replace(R.id.content, new MapFragment()).commit();
                    return true;
                case R.id.navigation_rewards:
                    transaction.replace(R.id.content, new RewardsFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation); //Disables the stupid default behaviour for bottom navs with more than 3 items.

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new TodayFragment()).commit();

        getUser();
    }

    public void getUser() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    getUser();
                } catch (Exception e) {
                    Log.e("getUser", "An error occurred.");
                }
            }
            private void getUser() throws Exception {
                URL url = new URL("http://77.53.126.163:8084/user?_id=5a2ade958b75881d2c6f6337");
                HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
                if (myConnection.getResponseCode() == 200) {
                    InputStream responseBody = myConnection.getInputStream();
                    InputStreamReader responseBodyReader =
                            new InputStreamReader(responseBody, "UTF-8");
                    JsonReader jsonReader = new JsonReader(responseBodyReader);
                    Log.i("getUser", "Start parsing JSON.");
                    readJson(jsonReader);
                } else {
                    Log.i("getUser", "HTTP code not 200.");
                }
                Log.i("getUser", "Done");
            }

            private void readJson(JsonReader jsonReader) throws Exception {
                Boolean success = false;
                String key;
                String name = "";
                jsonReader.beginObject(); // Start processing the JSON object
                while (jsonReader.hasNext()) { // Loop through all keys
                    key = jsonReader.nextName();
                    if (key.equals("success")) {
                        success = jsonReader.nextBoolean();
                    } else if (key.equals(("data"))) {
                        jsonReader.beginObject();
                        while(jsonReader.hasNext()) {
                            key = jsonReader.nextName();
                            if (key.equals("name")) {
                                name = jsonReader.nextString();
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();
                    } else {
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();
                Log.i("getUser", success.toString());
                Log.i("getUser", name);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
