package com.eflexsoft.soright;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;

public class SplashActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#0091EA"));
        setContentView(R.layout.activity_splash);

       new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

           @Override
           protected void onPostExecute(Void aVoid) {
               super.onPostExecute(aVoid);
               startActivity(new Intent(SplashActivity.this,MainActivity.class));
               finish();
           }
       }.execute();

    }
}
