package com.eflexsoft.soright.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eflexsoft.soright.R;
import com.eflexsoft.soright.adapter.NotifyAdapter;
import com.eflexsoft.soright.model.Notify;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class NotificationFragment extends DaggerFragment {

    RecyclerView recyclerView;

    @Inject
    FirebaseDatabase firebaseDatabase;

    @Inject
    FirebaseAuth firebaseAuth;
    List<Notify> notifies = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_notification, container, false);

       recyclerView = view.findViewById(R.id.notifyRecyclerView);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        NotifyAdapter notifyAdapter = new NotifyAdapter();
       recyclerView.setAdapter(notifyAdapter);

        DatabaseReference databaseReference = firebaseDatabase.getReference("Notify").child(firebaseAuth.getCurrentUser().getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notifies.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Notify notify = dataSnapshot.getValue(Notify.class);

                    notifies.add(notify);
                }
                notifyAdapter.setNotifies(notifies);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}
