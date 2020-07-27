package com.eflexsoft.soright;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eflexsoft.soright.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class PlaceOrderActivity extends DaggerAppCompatActivity {

    Intent intent;
    MaterialEditText email;
    MaterialEditText phoneNumber;
    MaterialEditText startDate;
    MaterialEditText endDate;

    TextView textViewUsername;
    TextView textViewId;

    @Inject
    FirebaseDatabase firebaseDatabase;

    @Inject
    FirebaseAuth firebaseAuth;

    String username;
    String userId;

    AlertDialog alertDialog;
    String name;
    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
        setContentView(R.layout.activity_place_order);

        intent = getIntent();
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");

        TextView toolbarName = findViewById(R.id.toolbar_name);
        Button orderBtn = findViewById(R.id.order_btn);

        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);
        startDate = findViewById(R.id.from);
        endDate = findViewById(R.id.to);
        textViewUsername = findViewById(R.id.username);
        textViewId = findViewById(R.id.id);

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage("Your order have been placed! if your order was not taken after two days contact us via Sorightmedia11@gmail.com, 08079140917, 08088778178")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialog = builder.create();

        toolbarName.setText(name + " " + price);
        orderBtn.setText("Order now " + price);

        DatabaseReference databaseReference = firebaseDatabase.getReference("Users")
                .child(firebaseAuth.getCurrentUser().getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User user = snapshot.getValue(User.class);
                username = user.getName();
                userId = user.getId();
                textViewUsername.setText("Logged in username: "+user.getName());
                textViewId.setText("Logged in user id: "+user.getId());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void finish(View view) {
        finish();
    }

    public void placeOrder(View view) {

        String getEmail = email.getText().toString();
        String getEPhoneNumber = phoneNumber.getText().toString();
        String getStartDate = startDate.getText().toString();
        String getEndDate = endDate.getText().toString();



        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("placing order....");

        if (getEmail.isEmpty() || getEPhoneNumber.isEmpty() || getStartDate.isEmpty() || getEndDate.isEmpty()) {
            Toast.makeText(this, "A filed is missing", Toast.LENGTH_SHORT).show();
        } else if (getEPhoneNumber.length() != 11) {
            Toast.makeText(this, "Incorrect phone number or not a nigerian phone number", Toast.LENGTH_SHORT).show();
        } else {

            progressDialog.show();

            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("email", getEmail);
            objectMap.put("phoneNumber", getEPhoneNumber);
            objectMap.put("startDate", getStartDate);
            objectMap.put("endDate", getEndDate);
            objectMap.put("Package",name+" "+price);
            objectMap.put("name", username);
            objectMap.put("id", userId);


            DatabaseReference databaseReference = firebaseDatabase.getReference("Orders");
            databaseReference.push().setValue(objectMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        alertDialog.show();
                    }
                }
            });

        }

    }
}
