package com.eflexsoft.soright.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eflexsoft.soright.R;
import com.eflexsoft.soright.adapter.CategoryAdapter;
import com.eflexsoft.soright.model.CategoryName;
import com.eflexsoft.soright.model.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment {
    RecyclerView recyclerView;

    @Inject
    FirebaseDatabase firebaseDatabase;

    AVLoadingIndicatorView avLoadingIndicatorView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.post_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        avLoadingIndicatorView = view.findViewById(R.id.progress);
        avLoadingIndicatorView.setVisibility(View.VISIBLE);

        ArrayList<Post> witheWedding = new ArrayList<>();

        Query witheWeddingReference = firebaseDatabase.getReference("Post").child("White Wedding").limitToLast(20).orderByValue();
        witheWeddingReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                witheWedding.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Post post = dataSnapshot.getValue(Post.class);
                    witheWedding.add(post);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayList<Post> traditionWedding = new ArrayList<>();

        Query traditionWeddingReference = firebaseDatabase.getReference("Post").child("Traditional Wedding").limitToLast(20);
        traditionWeddingReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                traditionWedding.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Post post = dataSnapshot.getValue(Post.class);
                    traditionWedding.add(post);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayList<Post> Anniversary = new ArrayList<>();

        Query AnniversaryReference = firebaseDatabase.getReference("Post").child("Anniversary").limitToLast(20);
        AnniversaryReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Anniversary.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Post post = snapshot.getValue(Post.class);
                    Anniversary.add(post);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        ArrayList<Post> Birthday = new ArrayList<>();

        Query BirthdayReference = firebaseDatabase.getReference("Post").child("Birthday").limitToLast(20);
        BirthdayReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Birthday.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Post post = dataSnapshot.getValue(Post.class);
                    Birthday.add(post);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayList<Post> Burial = new ArrayList<>();

        Query BurialReference = firebaseDatabase.getReference("Post").child("Burial").limitToLast(20);
        BurialReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Burial.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Post post = dataSnapshot.getValue(Post.class);
                    Burial.add(post);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        ArrayList<Post> Others = new ArrayList<>();

        Query OthersReference = firebaseDatabase.getReference("Post").child("Others").limitToLast(20);
        OthersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Others.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Post post = dataSnapshot.getValue(Post.class);
                    Others.add(post);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        witheWedding.add(new Post(" eye opens wide","https://firebasestorage.googleapis.com/v0/b/soright-9c428.appspot.com/o/PostImages%2Fjpg.1594649909923?alt=media&token=fb072cfe-cb13-49fb-8bca-9e45827672b5"));
//        witheWedding.add(new Post("bad boys","https://firebasestorage.googleapis.com/v0/b/soright-9c428.appspot.com/o/PostImages%2Fjpg.1594643910253?alt=media&token=be4da67d-50d3-42f7-82c4-867faa9c93e5"));
//        witheWedding.add(new Post("meme","https://firebasestorage.googleapis.com/v0/b/soright-9c428.appspot.com/o/PostImages%2Fjpg.1594675356753?alt=media&token=81518846-4be8-49e1-9a85-5c5fcd553ffe"));
        List<CategoryName> categoryAdapterList = new ArrayList<>();
        categoryAdapterList.add(new CategoryName("White Wedding", witheWedding));
        categoryAdapterList.add(new CategoryName("Traditional Wedding", traditionWedding));
        categoryAdapterList.add(new CategoryName("Anniversary", Anniversary));
        categoryAdapterList.add(new CategoryName("Birthday", Birthday));
        categoryAdapterList.add(new CategoryName("Burial", Burial));
        categoryAdapterList.add(new CategoryName("Others", Others));

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(3000);
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

                CategoryAdapter adapter = new CategoryAdapter(categoryAdapterList, getContext());

                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

        }.execute();

        return view;
    }
}
