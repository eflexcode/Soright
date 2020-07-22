package com.eflexsoft.soright.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.eflexsoft.soright.LoginActivity;
import com.eflexsoft.soright.PlaceOrderActivity;
import com.eflexsoft.soright.R;
import com.eflexsoft.soright.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class GoldFragment extends DaggerFragment {

    @Inject
    FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gold, container, false);

        Button button = view.findViewById(R.id.gold_btn);

        AlertDialog.Builder builder = new  AlertDialog.Builder(getContext())
                .setMessage("Your not logged in")
                .setNegativeButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    }
                }).setPositiveButton("Sign up", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(getContext(), SignUpActivity.class));
                    }
                });
        AlertDialog alertDialog = builder.create();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (firebaseAuth.getCurrentUser() == null){
                    alertDialog.show();
                }else {
                    startActivity(new Intent(getContext(), PlaceOrderActivity.class).putExtra("name","Gold").putExtra("price","₦650,000"));
                }

            }
        });

        return view;
    }
}
