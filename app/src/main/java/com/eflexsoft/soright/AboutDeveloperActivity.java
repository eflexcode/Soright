package com.eflexsoft.soright;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AboutDeveloperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
        setContentView(R.layout.activity_about_developer);
    }
}