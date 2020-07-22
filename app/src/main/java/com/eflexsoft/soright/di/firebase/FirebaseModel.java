package com.eflexsoft.soright.di.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class FirebaseModel {

    @Provides
    static FirebaseAuth firebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    static FirebaseDatabase firebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    @Provides
    static FirebaseStorage firebaseStorage(){
        return FirebaseStorage.getInstance();
    }

    @Provides
    static FirebaseFirestore firebaseFirestore(){
        return FirebaseFirestore.getInstance();
    }

    @Provides
    static FirebaseUser firebaseUser(FirebaseAuth firebaseAuth){
        return firebaseAuth.getCurrentUser();
    }

}
