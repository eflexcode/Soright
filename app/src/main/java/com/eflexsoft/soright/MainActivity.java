package com.eflexsoft.soright;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eflexsoft.soright.fragments.BookMeFragment;
import com.eflexsoft.soright.fragments.HomeFragment;
import com.eflexsoft.soright.fragments.MessageFragment;
import com.eflexsoft.soright.fragments.NotificationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    FrameLayout frameLayout;
    Fragment fragment;

    @Inject
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.botton_nav);
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.main_frame);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            setTitle("Home");
            fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        toolbar.setTitle("Home");
                        fragment = new HomeFragment();
                        break;

                    case R.id.book:
                        toolbar.setTitle("Book Me");
                        fragment = new BookMeFragment();
                        break;

                    case R.id.notification:
                        toolbar.setTitle("Notifications");
                        fragment = new NotificationFragment();
                        break;

                    case R.id.messages:
                        toolbar.setTitle("Messages");
                        fragment = new MessageFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.top_options, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.sign_out:
                firebaseAuth.signOut();
                Toast.makeText(this, "yor logged out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_app:
                showAboutAppDialog();
                break;
            case R.id.About_app_developer:
                startActivity(new Intent(MainActivity.this,AboutDeveloperActivity.class));
        }
        return true;
    }

    private void showAboutAppDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage("The app if quiet self explanatory, but in case you open the app and you did not see any photo in the home view click home button again or try scrolling up and down ")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
      AlertDialog  alertDialog = builder.create();
      alertDialog.show();
    }
}
