package com.eflexsoft.soright.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eflexsoft.soright.R;
import com.eflexsoft.soright.adapter.CategoryAdapter;
import com.eflexsoft.soright.model.ChatList;
import com.eflexsoft.soright.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class MessageFragment extends DaggerFragment {
    RecyclerView recyclerView;

    @Inject
    FirebaseDatabase firebaseDatabase;

    FirebaseAuth firebaseAuth;

    AVLoadingIndicatorView avLoadingIndicatorView;

    List<ChatList> chatListList = new ArrayList<>();
    List<User> userList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        recyclerView = view.findViewById(R.id.messageRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        avLoadingIndicatorView = view.findViewById(R.id.progress);
        avLoadingIndicatorView.setVisibility(View.VISIBLE);

        DatabaseReference databaseReference = firebaseDatabase.getReference("ChatList")
                .child(firebaseAuth.getCurrentUser().getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChatList chatList = dataSnapshot.getValue(ChatList.class);
                    chatListList.add(chatList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(5000);
                    avLoadingIndicatorView.setVisibility(View.VISIBLE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                avLoadingIndicatorView.setVisibility(View.GONE);


            }

        }.execute();

        return view;
    }
}
